package mdtg.business.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangYunwei [2026-04-07]
 */
@Configuration
public class CustomBeans {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

//    @Bean
//    public MinioClient minioClient() {
//
//        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
//    }
}
