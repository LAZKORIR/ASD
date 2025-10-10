package com.practice.apigateway.security;

import com.practice.apigateway.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    private static final String AUTH_HEADER = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // ✅ Skip /auth endpoints (so you can log in)
        if (path.startsWith("/auth/")) {
            return chain.filter(exchange);
        }

        // ✅ Validate header presence
        String authHeader = exchange.getRequest().getHeaders().getFirst(AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return this.onError(exchange, "Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
        }

        // ✅ Extract token
        String token = authHeader.substring(7);

        // ✅ Validate token signature & expiry
        if (!jwtUtil.validateToken(token)) {
            return this.onError(exchange, "Invalid or expired JWT token", HttpStatus.UNAUTHORIZED);
        }

        // ✅ Extract username and forward to microservices
        String username = jwtUtil.extractUsername(token);

        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(r -> r.headers(h -> {
                    h.add("X-Authenticated-User", username);
                    h.add("X-Gateway-Verified", "true");
                }))
                .build();

        return chain.filter(modifiedExchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String body = "{\"error\": \"" + message + "\"}";
        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse()
                        .bufferFactory()
                        .wrap(body.getBytes())));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
