package mdtg.business.common;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author WangYunwei [2026-04-07]
 */
@Slf4j
@Configuration
public class CustomBeans {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("tcp://${service-address.mqtt.ip}:${service-address.mqtt.port}")
    private String serverURI;

    @Value("${spring.application.name}")
    private String clientId;

    @Value("${service-address.mqtt.username}")
    private String userName;

    @Value("${service-address.mqtt.password}")
    private String password;

    @Bean
    public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        MinioClient minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("minio_manager_api").build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("minio_manager_api").build());
        } else {
            System.out.println("Bucket 'minio_manager_api' already exists.");
        }
        return minioClient;
    }

    @Bean
    public MqttClient mqttClient() throws MqttException {

        MqttClient mqClient = new MqttClient(serverURI, clientId, new MemoryPersistence());
        // 设置连接选项
        MqttConnectionOptions connOpts = new MqttConnectionOptions();
        connOpts.setUserName(userName);
        connOpts.setPassword(password.getBytes());
        connOpts.setAutomaticReconnect(true);
        mqClient.setCallback(new CustomBeans.MqttCallbackImpl());
        mqClient.connect(connOpts);
        return mqClient;
    }

    static class MqttCallbackImpl implements MqttCallback {

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
        }

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
//            log.info(">>> Topic: {}, Qos: {}, Retained: {}, message: {}", topic, message.getQos(), message.isRetained(), new String(message.getPayload()));
        }

        @Override
        public void deliveryComplete(IMqttToken token) {
            // 已投递
//            log.info("deliveryComplete --------- {}", token.isComplete());
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
    }
}