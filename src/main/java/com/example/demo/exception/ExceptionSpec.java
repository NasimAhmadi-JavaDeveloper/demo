package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionSpec {

    SERVICE_NOT_FOUND("service not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),
    PERMISSION_ERROR("only SIMPLE users can be granted service permissions", HttpStatus.BAD_REQUEST),
    CREDIT_ERROR("increasing the amount of credit is possible for simple users", HttpStatus.BAD_REQUEST),


    ;
    private final String message;
    private final HttpStatus httpStatus;
}
