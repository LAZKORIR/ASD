package com.practice.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback/auth")
    public ResponseEntity<Map<String, String>> authFallback() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "⚠ Auth Service is unavailable, please try again later.");
        System.out.println("Auth service fallback triggered");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @GetMapping("/fallback/course")
    public ResponseEntity<Map<String, String>> courseFallback() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "⚠ Course Service is unavailable, please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @GetMapping("/fallback/enrollment")
    public ResponseEntity<Map<String, String>> enrollmentFallback() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "⚠ Enrollment Service is unavailable, please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }
}

