package com.practice.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping(value = "/auth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Map<String, String>> authFallback() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "⚠ Auth Service is unavailable, please try again later.");
        System.out.println("Auth service fallback triggered");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @RequestMapping(value = "/course", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Map<String, String>> courseFallback() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "⚠ Course Service is unavailable, please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @RequestMapping(value = "/enrollment", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Map<String, String>> enrollmentFallback() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "⚠ Enrollment Service is unavailable, please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @GetMapping("/fallback/ratelimit")
    public ResponseEntity<Map<String, String>> rateLimitFallback() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "⚠ You are being rate limited. Please slow down.");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(body);
    }

}

