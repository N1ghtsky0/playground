package com.jiwook.playground.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jiwook.playground.dto.reponse.TokenDTO;
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
import java.util.Base64;
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

    public String createAccessToken(String username, String uuid) {
        final long currentMillis = System.currentTimeMillis();
        return Jwts.builder()
                .subject(username)
                .id(uuid)
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

    /**
     * 리프레쉬 토큰을 이용하여 토큰 재생성
     * @param token 리프레쉬 토큰
     * @return (리프레쉬토큰 사용 횟수 < 5) ? new 엑세스토큰, origin 리프레쉬 토큰 : new 엑세스토큰, new 리프레쉬 토큰
     */
    public TokenDTO reCreateToken(String token) {
        String accessToken;
        String refreshToken = token;

        Claims refreshTokenClaims = validateToken(token);
        RefreshToken refreshTokenEntity = refreshTokenRepo.findByUuid(refreshTokenClaims.getId());
        if (refreshTokenEntity.getRefreshCount() < 4) {
            accessToken = createAccessToken(refreshTokenClaims.getSubject(), refreshTokenClaims.getId());
            refreshTokenEntity.used();
            refreshTokenRepo.save(refreshTokenEntity);
        } else {
            accessToken = createAccessToken(refreshTokenClaims.getSubject(), UUID.randomUUID().toString());
            refreshToken = createRefreshToken(accessToken);
        }
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * 리프레쉬 토큰 유효성 검증
     * @param token 리프레쉬 토큰
     * @return 토큰 존재 유무 && 사용횟수 5회 미만
     */
    public Boolean isTokenExist(String token) {
        Claims refreshTokenClaims = validateToken(token);
        RefreshToken refreshToken = refreshTokenRepo.findByUuid(refreshTokenClaims.getId());
        return refreshToken != null && refreshToken.getRefreshCount() < 5;
    }

    public Claims validateToken(String token) {
        return Jwts.parser().verifyWith((SecretKey) getSignKey()).build().parseSignedClaims(token).getPayload();
    }

    public String getJtiFromExpiredToken(String token) {
        JsonObject payload = JsonParser.parseString(new String(Base64.getDecoder().decode(token.split("\\.")[1]))).getAsJsonObject();
        return payload.get("jti").toString().replace("\"", "");
    }

    private Key getSignKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
