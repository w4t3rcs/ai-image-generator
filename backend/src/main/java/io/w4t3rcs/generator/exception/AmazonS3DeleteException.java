package io.w4t3rcs.generator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AmazonS3DeleteException extends RuntimeException {
    public AmazonS3DeleteException(Throwable cause) {
        super(cause);
    }
}
