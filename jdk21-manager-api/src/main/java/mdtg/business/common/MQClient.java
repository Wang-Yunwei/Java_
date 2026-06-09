package mdtg.business.common;

import com.google.common.primitives.Bytes;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author WangYunwei [2026-04-17]
 */
@Slf4j
@Component
public class MQClient {

    public static final Set<String> ONLINE_HASH_SET = new HashSet<>();

    public static final Map<String, String> CURRENT_POINT_HASH_MAP = new HashMap<>();

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
    public final String ONLINE_JSON = "{\"status\":\"online\",\"version\":\"1.0.0\"}";

    public final String OFFLINE_JSON = "{\"status\":\"offline\",\"reason\":\"unexpected_disconnect\"}";

    private final byte[] STATUS_ONLINE_BYTES = {0x6f, 0x6e, 0x6c, 0x69, 0x6e, 0x65};

    private final byte[] STATUS_OFFLINE_BYTES = {0x6f, 0x66, 0x66, 0x6c, 0x69, 0x6e, 0x65};

    private final String[] topicFilters = {
            "mdtg/m_api/agv/status/+",
            "mdtg/m_api/agv/heartbeat/+",
            "mdtg/m_api/agv/current_point/+",
            "mdtg/m_api/badge/status/+",
            "mdtg/m_api/badge/heartbeat/+",
            "mdtg/m_api/head/status/+",
            "mdtg/m_api/head/heartbeat/+"
    };

    private final int[] qos = {0, 0, 0, 0, 0, 0, 0};

    @Value("tcp://${service-address.mqtt.ip}:${service-address.mqtt.port}")
    private String serverURI;

    @Value("${spring.application.name}")
    private String clientId;

    @Value("${service-address.mqtt.username}")
    private String userName;

    @Value("${service-address.mqtt.password}")
    private String password;

    /**
     * <dl>
     *   <dt>单层通配符: STS/M350/PUBLISH/+</dt>
     *   <dd>STS/M350/PUBLISH/+ 将匹配 STS/M350/PUBLISH/x26123 和 STS/M350/PUBLISH/x26127，但不会匹配 STS/M350/PUBLISH/x26123/subtopic</dd>
     *   <dt>多层通配符: STS/M350/PUBLISH/#</dt>
     *   <dd>STS/M350/PUBLISH/# 将匹配 STS/M350/PUBLISH/x26123、STS/M350/PUBLISH/x26127 和 STS/M350/PUBLISH/x26123/subtopic</dd>
     * </dl>
     */
    public static void main(String[] args) {

        try {
            new MQClient().testFun();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    private void testFun() throws MqttException {

        MqttClient mqClient = new MqttClient("tcp://58.211.186.6:1883", "M_API", new MemoryPersistence());
        // 设置连接选项
        MqttConnectionOptions connOpts = new MqttConnectionOptions();
        connOpts.setUserName("mdtg");
        connOpts.setPassword("mdtg@123".getBytes());
        connOpts.setAutomaticReconnect(true);

        /*
          --- 核心策略 A: Keepalive ---
          设置 60秒心跳间隔。
          如果 60 * 3 秒内无通信，客户端自动发 PINGREQ；若 1.5倍时间(90s)无响应，Broker判定掉线。
         */
        connOpts.setConnectionTimeout(30);
        connOpts.setKeepAliveInterval(60 * 3);

        /*
          --- 核心策略 B: 遗愿消息 ---
          预设 "遗嘱": 如果非正常断开 (断电/崩溃), Broker会自动发布这条消息
          注意: retain=true, 确保订阅者能立即收到最后的离线状态
         */
        MqttMessage message = new MqttMessage(OFFLINE_JSON.getBytes());
        message.setQos(1);
        message.setRetained(true);
        connOpts.setWill("mdtg/m_api/test/status", message);

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

    public void mqttClient() throws MqttException {

        MqttClient mqClient = new MqttClient(serverURI, clientId, new MemoryPersistence());
        MqttConnectionOptions connOpts = new MqttConnectionOptions();
        connOpts.setUserName(userName);
        connOpts.setPassword(password.getBytes());
        connOpts.setAutomaticReconnect(true);
        connOpts.setKeepAliveInterval(60 * 5);
        mqClient.setCallback(new MqttCallback() {

            @Override
            public void disconnected(MqttDisconnectResponse disconnectResponse) {

            }

            @Override
            public void mqttErrorOccurred(MqttException exception) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {

                String macAddress = topic.substring(topic.lastIndexOf("/") + 1).toLowerCase();
                if (topic.startsWith("mdtg/m_api/agv/status/") || topic.startsWith("mdtg/m_api/badge/status/") || topic.startsWith("mdtg/m_api/head/status/")) {
                    if (Bytes.indexOf(message.getPayload(), STATUS_ONLINE_BYTES) >= 0) {
                        ONLINE_HASH_SET.add(macAddress);
                    } else if (Bytes.indexOf(message.getPayload(), STATUS_OFFLINE_BYTES) >= 0) {
                        ONLINE_HASH_SET.remove(macAddress);
                    }
                    log.info("当前在线设备列表: {}", ONLINE_HASH_SET);
                } else if (topic.startsWith("mdtg/m_api/agv/current_point/")) {
                    CURRENT_POINT_HASH_MAP.put(macAddress, new String(message.getPayload()));
                    log.info("当前设备最新坐标点: {}", CURRENT_POINT_HASH_MAP);
                }
            }

            @Override
            public void deliveryComplete(IMqttToken token) {

            }

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

                try {
                    mqClient.subscribe(topicFilters, qos);
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void authPacketArrived(int reasonCode, MqttProperties properties) {

            }
        });
        mqClient.connect(connOpts); // 建立连接
    }
}
