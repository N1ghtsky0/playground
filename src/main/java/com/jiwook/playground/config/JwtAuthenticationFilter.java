package com.jiwook.playground.config;

import com.jiwook.playground.dto.reponse.TokenDTO;
import com.jiwook.playground.utils.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String accessToken = getAccessToken(request);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ANONYMOUS"));

        if (accessToken != null) {
            try {
                Claims accessTokenClaims = tokenProvider.validateToken(accessToken);
                authorities.add(new SimpleGrantedAuthority("USER"));
            } catch (ExpiredJwtException e) {
                log.warn("ExpiredJwtException!!!");
                final String refreshToken = getRefreshToken(request);
                try {
                    if (refreshToken != null && tokenProvider.isTokenExist(refreshToken)) {
                        Claims refreshTokenClaims = tokenProvider.validateToken(refreshToken);
                        if (!refreshTokenClaims.getId().equals(tokenProvider.getJtiFromExpiredToken(accessToken))) {
                            throw new IllegalArgumentException("엑세스 토큰 - 리프레쉬 토큰 발급 ID 불일치");
                        }
                        TokenDTO tokenDTO = tokenProvider.reCreateToken(refreshToken);
                        response.setHeader("Authorization", tokenDTO.getAccessToken());

                        if (!refreshToken.equals(tokenDTO.getRefreshToken())) {
                            Cookie refreshCookie = new Cookie("refresh_token", tokenDTO.getRefreshToken());
                            refreshCookie.setHttpOnly(true);
                            refreshCookie.setSecure(true);
                            refreshCookie.setDomain("localhost");
                            refreshCookie.setPath("/");
                            response.addCookie(refreshCookie);
                        }

                        authorities.add(new SimpleGrantedAuthority("USER"));
                    }
                } catch (Exception e2) {
                    log.warn("Refresh Token Exception!!!");
                }
            } catch (JwtException e) {
                log.warn("JwtException!!!");
            } catch (IllegalArgumentException e) {
                log.warn("IllegalArgumentException!!!");
            }
        }

        User userDetails = new User("username", "", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .filter(token -> token.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    private String getRefreshToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refresh_token"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }
}
