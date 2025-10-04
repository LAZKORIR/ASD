package com.practice.apigateway.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatus(ResponseStatusException ex) {
        if (ex.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
            Map<String, String> body = new HashMap<>();
            body.put("message", "⚠ Too many requests, please try again later.");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(body);
        }
        // fallback for other status exceptions
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getReason() != null ? ex.getReason() : "Unexpected error");
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }
}
