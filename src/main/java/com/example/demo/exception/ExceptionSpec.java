package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionSpec {

    SERVICE_NOT_FOUND("service not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),

    ;
    private final String message;
    private final HttpStatus httpStatus;
}
