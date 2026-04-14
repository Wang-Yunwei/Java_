package mdtg.business.common.config;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.MakeBucketArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    @Bean
    public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        MinioClient minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
        // Make 'asiatrip' bucket if not exist.
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
        } else {
            System.out.println("Bucket 'asiatrip' already exists.");
        }
        return minioClient;
    }
}
