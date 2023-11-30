package jiwook.kim.playground.base.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jiwook.kim.playground.base.common.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@PropertySource("classpath:jwt.yml")
@Service
public class TokenProvider {
    private final String SECRET_KEY;
    private final String ISSUER;
    private final Long ACCESS_TOKEN_EXPIRATION = (long) 60 * 60;  // 1 HOUR
    private final Long REFRESH_TOKEN_EXPIRATION = (long) 60 * 60 * 24;    // 1 DAY

    public TokenProvider(
            @Value("${issuer}") String issuer,
            @Value("${secret-key}") String secretKey) {
        this.ISSUER = issuer;
        this.SECRET_KEY = secretKey;
    }

    public String createToken(String userSpecification, TokenType type) {
        Long expiration = type.equals(TokenType.ACCESS) ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;

        return Jwts.builder()
                .signWith(new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName()))
                .setSubject(userSpecification)
                .setIssuer(ISSUER)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(expiration, ChronoUnit.SECONDS)))
                .compact();
    }

}
