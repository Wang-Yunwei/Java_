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
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author WangYunwei [2026-04-07]
 */
@Slf4j
@Configuration
public class CustomBeans {

    private final MQClient mqClient;

    @Value("${service-address.minio.endpoint}")
    private String endpoint;

    @Value("${service-address.minio.access-key}")
    private String accessKey;

    @Value("${service-address.minio.secret-key}")
    private String secretKey;

    @Value("${service-address.minio.bucket-name}")
    private String bucketName;

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
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            System.out.println("Bucket 'mdtg-esp32-api' already exists.");
        }
        return minioClient;
    }

//    @Bean
//    public ThreadPoolTaskScheduler taskScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(10);              // 根据任务并发量调整
//        scheduler.setThreadNamePrefix("agv-scheduler-");
//        scheduler.setAwaitTerminationSeconds(30);
//        scheduler.setWaitForTasksToCompleteOnShutdown(true);
//        scheduler.setErrorHandler(t -> LoggerFactory.getLogger(DynamicScheduleManager.class).error("调度线程未捕获异常", t));
//        return scheduler;
//    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10); // 设置线程池大小，根据任务数量调整
        scheduler.setThreadNamePrefix("agv-task-"); // 设置线程名前缀，便于识别
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setRemoveOnCancelPolicy(true); // 任务取消后立即从线程池移除
        scheduler.initialize();
        return scheduler;
    }
}