package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    public static final String SUCCESS = "success";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("MethodArgumentNotValidException: {}", ex.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->
                errorMap.put(e.getField(), e.getDefaultMessage()
                ));

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, "Payload validation error");
        problemDetail.setProperty(SUCCESS, false);
        problemDetail.setProperty("validation", errorMap);
        return problemDetail;
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("ResourceNotFoundException: {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty(SUCCESS, false);
        return problemDetail;
    }
}
