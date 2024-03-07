package com.jiwook.playground.utils;

import com.jiwook.playground.entity.RefreshToken;
import com.jiwook.playground.repository.RefreshTokenRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@PropertySource("classpath:jwt.yml")
@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final RefreshTokenRepo refreshTokenRepo;

    @Value("${secret-key}")
    private String SECRET_KEY;

    private final Long ACCESS_TOKEN_EXPIRATION = (long) 1000 * 60 * 30;
    private final Long ACCESS_REFRESH_EXPIRATION = (long) 1000 * 60 * 60 * 24 * 7;

    public String createAccessToken(String username) {
        final long currentMillis = System.currentTimeMillis();
        return Jwts.builder()
                .subject(username)
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(currentMillis))
                .expiration(new Date(currentMillis + ACCESS_TOKEN_EXPIRATION))
                .signWith(getSignKey())
                .compact();
    }

    /**
     * 리프레쉬 토큰 생성
     * @param accessToken 엑세스 토큰의 정보를 바탕으로 리프레쉬 토큰 생성
     * @return (String) refreshToken
     */
    public String createRefreshToken(String accessToken) {
        final long currentMillis = System.currentTimeMillis();
        final Claims claims = validateToken(accessToken);
        final String refreshToken = Jwts.builder()
                .subject(claims.getSubject())
                .id(claims.getId())
                .issuedAt(new Date(currentMillis))
                .expiration(new Date(currentMillis + ACCESS_REFRESH_EXPIRATION))
                .signWith(getSignKey())
                .compact();
        refreshTokenRepo.save(RefreshToken.builder()
                .uuid(claims.getId())
                .build());
        return refreshToken;
    }

    public Claims validateToken(String token) {
        return Jwts.parser().verifyWith((SecretKey) getSignKey()).build().parseSignedClaims(token).getPayload();
    }

    private Key getSignKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
