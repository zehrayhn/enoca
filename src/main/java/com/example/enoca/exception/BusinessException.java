package com.example.enoca.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String error;
    public BusinessException(HttpStatus httpStatus, String error) {
        super(String.join(", ", error));
        this.httpStatus = httpStatus;
        this.error = error;
    }
}
