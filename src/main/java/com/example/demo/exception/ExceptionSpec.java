package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionSpec {

    SERVER_ERROR("Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_NOT_FOUND("service not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),
    USER_SHOULD_BE_SIMPLE("user should be simple", HttpStatus.BAD_REQUEST),
    CREDIT_ERROR("increasing the amount of credit is possible for simple users", HttpStatus.BAD_REQUEST),
    ADMIN_USER_CAN_ACTIVATE("admin user can activate services", HttpStatus.BAD_REQUEST),
    ADMIN_USER_CAN_DEACTIVATE("admin user can deactivate services", HttpStatus.BAD_REQUEST),
    SERVICE_NOT_ACTIVE("service is not active", HttpStatus.BAD_REQUEST),
    PERMISSION_DENIED("you do not have permission to use this service", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_CREDIT("you do not have enough credit to use this service", HttpStatus.BAD_REQUEST),
    MAX_USAGE_LIMIT_REACHED("you have used this service the maximum number of allowed times", HttpStatus.BAD_REQUEST),
    USER_NAME_NOT_FOUND_EXCEPTION("user name not found", HttpStatus.NOT_FOUND),



    ;
    private final String message;
    private final HttpStatus httpStatus;
}
