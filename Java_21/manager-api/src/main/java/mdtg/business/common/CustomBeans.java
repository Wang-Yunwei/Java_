package mdtg.business.common;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
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

    private final MQClient mqClient;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket-name:mdtg-esp32-api}")
    private String bucket;

    public CustomBeans(MQClient mqClient) {

        this.mqClient = mqClient;
    }

    @Bean
    ApplicationRunner startImmediatelyExecute() {

        return args -> {
            log.info("================== 【START-UP SUCCESSFUL】 ==================");
            if ("Linux".equals(System.getProperties().getProperty("os.name"))) {
                mqClient.mqttClient();
            }
        };
    }

    @Bean
    public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        MinioClient minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        } else {
            System.out.println("Bucket 'mdtg-esp32-api' already exists.");
        }
        return minioClient;
    }
}