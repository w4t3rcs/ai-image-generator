package io.w4t3rcs.generator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AmazonS3InsertionException extends RuntimeException {
    public AmazonS3InsertionException(String message) {
        super(message);
    }

    public AmazonS3InsertionException(Throwable cause) {
        super(cause);
    }
}
