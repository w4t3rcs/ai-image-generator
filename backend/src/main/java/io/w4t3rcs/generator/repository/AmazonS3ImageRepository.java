package io.w4t3rcs.generator.repository;

import io.w4t3rcs.generator.exception.AmazonS3DeleteException;
import io.w4t3rcs.generator.exception.AmazonS3InsertionException;
import io.w4t3rcs.generator.exception.AmazonS3ReadException;
import org.springframework.ai.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Repository
public class AmazonS3ImageRepository implements ImageRepository {
    private final S3Client s3Client;
    private final String bucketName;

    @Autowired
    public AmazonS3ImageRepository(S3Client s3Client, @Value("${aws.s3.bucket.name}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    @Override
    public Long save(Image entity) {
        String b64Json = entity.getB64Json();
        if (b64Json != null) {
            try {
                Long id = System.currentTimeMillis();
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(String.valueOf(id))
                        .build();
                s3Client.putObject(putObjectRequest, RequestBody.fromString(b64Json));
                return id;
            } catch (AwsServiceException | SdkClientException e) {
                throw new AmazonS3InsertionException(e);
            }
        } else {
            throw new AmazonS3InsertionException("B64Json of the image must not be null");
        }
    }

    @Override
    public Image findById(Long id) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(String.valueOf(id))
                .build();
        try (ResponseInputStream<GetObjectResponse> clientObject = s3Client.getObject(getObjectRequest)) {
            byte[] bytes = clientObject.readAllBytes();
            String b64Json = new String(bytes, StandardCharsets.UTF_8);
            return new Image(null, b64Json);
        } catch (IOException e) {
            throw new AmazonS3ReadException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(String.valueOf(id))
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
        } catch (AwsServiceException | SdkClientException e) {
            throw new AmazonS3DeleteException(e);
        }
    }
}
