package io.w4t3rcs.generator.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Data
@Configuration
public class AmazonS3Config {
    @Bean
    public S3Client amazonS3Client(@Value("${aws.s3.region}") String region,
                                   @Value("${aws.s3.access.key.id}") String accessKeyId,
                                   @Value("${aws.s3.secret.access.key}") String secretAccessKey) {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .build();
    }
}
