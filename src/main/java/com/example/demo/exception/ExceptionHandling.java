package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LogicalException.class)
    public Object handleBusinessExceptions(LogicalException e) {
        log.error("Business Error Occurred! {}", e.getMessage());
        return createErrorResponse(e);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Object handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DB exception Occurred! {}", e.getMessage());
        return mapException(e);
    }

    @ExceptionHandler(Throwable.class)
    public Object handleUnhandled(Exception e) {
        return mapException(e);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(LogicalException e) {
        return ResponseEntity
                .status(e.getSpecs().getHttpStatus())
                .body(new ErrorResponse(e.getSpecs().getMessage()));
    }

    private Object mapException(Exception e) {
        log.error("Exception Occurred!", e);
        return ResponseEntity
                .status(ExceptionSpec.SERVER_ERROR.getHttpStatus())
                .body(new ErrorResponse(ExceptionSpec.SERVER_ERROR.getMessage()));
    }
}
