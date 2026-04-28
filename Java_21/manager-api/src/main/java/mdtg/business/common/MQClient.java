package mdtg.business.common;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.stereotype.Component;

/**
 * @author WangYunwei [2026-04-17]
 */
@Slf4j
@Component
public class MQClient {

    /**
     * <dl>
     *   <dt>上行数据 (设备 -> 服务器)</dt>
     *   <dd>mdtg/m_api/agv/heartbeat/{DEVICE_ID}   # 心跳保活</dd>
     *   <dd>mdtg/m_api/agv/status/{DEVICE_ID}      # 状态变更 (在线/离线/故障)</dd>
     *   <dd>mdtg/m_api/agv/telemetry/{DEVICE_ID}   # 遥测数据 (位置、电量、速度)</dd>
     *   <dd>mdtg/m_api/agv/logs/{DEVICE_ID}        # 错误日志</dd>
     *   <dt>下行数据 (服务器 -> 设备)</dt>
     *   <dd>mdtg/m_api/agv/cmd/{DEVICE_ID}         # 单个设备指令</dd>
     *   <dd>mdtg/m_api/agv/cmd/all                 # 广播指令 (可选)</dd>
     * </dl>
     * {"map_name": "WHEELTEC", "x": 3.803025, "y": -7.810509, "yaw": -2.950007, "updated_at": 1776265404, mac_address: "xx"}
     */
    public static final String TOPIC_AGV_HEARTBEAT = "mdtg/m_api/agv/heartbeat/%s";

    public static final String TOPIC_AGV_STATUS = "mdtg/m_api/agv/status/%s";

    public static final String TOPIC_BADGE_HEARTBEAT = "mdtg/m_api/badge/heartbeat/%s";

    public static final String TOPIC_BADGE_STATUS = "mdtg/m_api/badge/status/%s";

    public static final String AGV_HEARTBEAT_JSON = "{\"currentPosition\":{\"x\":%f,\"y\":%f,\"yaw\":%f},\"battery\":%b,\"cpuLoad\":%.1f}";

    public static final String ONLINE_JSON = "{\"status\":\"online\",\"version\":\"1.0.0\"}";

    public static final String OFFLINE_JSON = "{\"status\":\"offline\",\"reason\":\"unexpected_disconnect\"}";

    public static final String PUB_AGV_CONTROL_CMD = "mdtg/control_cmd/%s";

    /**
     * <dl>
     *   <dt>单层通配符: STS/M350/PUBLISH/+</dt>
     *   <dd>STS/M350/PUBLISH/+ 将匹配 STS/M350/PUBLISH/x26123 和 STS/M350/PUBLISH/x26127，但不会匹配 STS/M350/PUBLISH/x26123/subtopic</dd>
     *   <dt>多层通配符: STS/M350/PUBLISH/#</dt>
     *   <dd>dTS/M350/PUBLISH/# 将匹配 TS/M350/PUBLISH/x26123、TS/M350/PUBLISH/x26127 和 TS/M350/PUBLISH/x26123/subtopic</dd>
     * </dl>
     */
    public static void main(String[] args) throws MqttException {

        MqttClient mqClient = new MqttClient("tcp://58.211.186.6:1883", "M_API", new MemoryPersistence());
        // 设置连接选项
        MqttConnectionOptions connOpts = new MqttConnectionOptions();
        connOpts.setUserName("mdtg");
        connOpts.setPassword("mdtg@123".getBytes());
        connOpts.setAutomaticReconnect(true);

        /*
          --- 核心策略 A: Keepalive ---
          设置 60 秒心跳间隔。
          如果 60秒内无通信，客户端自动发 PINGREQ；若 1.5倍时间(90s)无响应，Broker判定掉线。
         */
        connOpts.setConnectionTimeout(30);
        connOpts.setKeepAliveInterval(60);

        /*
          --- 核心策略 B: 遗愿消息 ---
          预设 "遗嘱": 如果非正常断开 (断电/崩溃), Broker会自动发布这条消息
          注意: retain=true, 确保订阅者能立即收到最后的离线状态
         */
        MqttMessage message = new MqttMessage(OFFLINE_JSON.getBytes());
        message.setQos(1);
        message.setRetained(true);
        connOpts.setWill(TOPIC_AGV_STATUS, message);

        // 设置回调
        mqClient.setCallback(new MqttCallback() {

            @Override
            public void disconnected(MqttDisconnectResponse disconnectResponse) {

                log.info("disconnected --------- {}", disconnectResponse.getReturnCode());
            }

            @Override
            public void mqttErrorOccurred(MqttException exception) {

                log.info("mqttErrorOccurred --------- {}", exception.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {

                // 消息到达
                log.info(">>> Topic: {}, Qos: {}, Retained: {}, message: {}", topic, message.getQos(), message.isRetained(), new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttToken token) {

                // 已投递
//            log.info("deliveryComplete --------- {}", token.isComplete());
            }

            /**
             * 当与服务器的连接成功完成时调用
             * Called when the connection to the server is completed successfully.
             *
             * @param reconnect If true, the connection was the result of automatic reconnect.
             * @param serverURI The server URI that the connection was made to.
             */
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

                log.info("connectComplete --------- {}", serverURI);
                /*
                  --- 核心策略 C: 上线覆盖 ---
                  连接成功后, 立即发布"在线"消息, 覆盖掉之前的遗愿消息
                  注意: retain=true, 确保订阅者能立即收到最新的在线状态
                 */
                try {
                    mqClient.publish("mdtg/m_api/agv/status/00:00:00:00", ONLINE_JSON.getBytes(), 1, true);
                    mqClient.publish("mdtg/m_api/agv/status/00:00:00:01", ONLINE_JSON.getBytes(), 1, true);
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * 当客户端接收到 AUTH 包时调用
             * Called when an AUTH packet is received by the client.
             *
             * @param reasonCode The Reason code, can be Success (0), Continue authentication (24)
             *                   or Re-authenticate (25).
             * @param properties The {@link MqttProperties} to be sent, containing the
             *                   Authentication Method, Authentication Data and any required User
             *                   Defined Properties.
             */
            @Override
            public void authPacketArrived(int reasonCode, MqttProperties properties) {

                log.info("authPacketArrived --------- {}", reasonCode);
            }
        });
        // 建立连接
        mqClient.connect(connOpts);
        // 订阅
        mqClient.subscribe("mdtg/m_api/agv/status/+", 0);
        mqClient.subscribe("mdtg/m_api/agv/heartbeat/+", 0);

    }
}
