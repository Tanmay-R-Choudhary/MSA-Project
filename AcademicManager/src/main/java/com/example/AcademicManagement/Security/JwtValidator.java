package com.example.AcademicManagement.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtValidator {

    private final String secret = "your-secret-key-should-be-at-least-256-bits-long";

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Integer getUserIdFromToken(String token) {
        Claims claims = validateToken(token);
        return claims != null ? (Integer) claims.get("userId") : null;
    }
}