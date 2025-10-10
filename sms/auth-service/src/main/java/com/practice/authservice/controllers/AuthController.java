package com.practice.authservice.controllers;

import com.practice.authservice.model.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Must match JwtUtil in Gateway
    private static final String SECRET_KEY = "MySuperSecretKeyForJWTValidation12345";
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("============= Login endpoint hit =====================");
        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());

        // Simple static auth
        if (!"john_doe".equals(loginRequest.getUsername()) || !"password123".equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
        }

        // Create JWT valid for 1 hour
        String token = Jwts.builder()
                .setSubject(loginRequest.getUsername())
                .claim("role", "USER")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Map<String, String> response = new HashMap<>();
        response.put("username", loginRequest.getUsername());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
