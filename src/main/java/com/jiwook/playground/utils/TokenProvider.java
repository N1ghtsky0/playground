package com.jiwook.playground.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@PropertySource("classpath:jwt.yml")
@Service
public class TokenProvider {
    @Value("${secret-key}")
    private String SECRET_KEY;

    private final Long ACCESS_TOKEN_EXPIRATION = (long) 1000 * 60 * 30;

    public String createToken(String username) {
        final long currentMillis = System.currentTimeMillis();
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(currentMillis))
                .expiration(new Date(currentMillis + ACCESS_TOKEN_EXPIRATION))
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
