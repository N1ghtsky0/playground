package jiwook.kim.playground.base.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jiwook.kim.playground.Entity.Account;
import jiwook.kim.playground.Entity.RefreshToken;
import jiwook.kim.playground.base.common.TokenType;
import jiwook.kim.playground.repository.AccountRepo;
import jiwook.kim.playground.repository.RefreshTokenRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final AccountRepo accountRepo;
    private final RefreshTokenRepo refreshTokenRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseBearerToken(request, TokenType.ACCESS);
            User user = parseUserSpecification(token);
            AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, token, user.getAuthorities());

            authenticated.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        } catch (ExpiredJwtException e) {
            reIssueAccessToken(request, response, e);
        } catch (SignatureException | MalformedJwtException e) {
            request.setAttribute("exception", e);
        } catch (Exception e) {
            log.error("Error without JWT!!");
            log.error(e.toString());
            request.setAttribute("exception", e);
        }
        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request, TokenType type) {
        String headerName = (type.equals(TokenType.ACCESS)) ? HttpHeaders.AUTHORIZATION : "Refresh-Token";
        return Optional.ofNullable(request.getHeader(headerName))
                .filter(token -> token.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    private User parseUserSpecification(String token) {
        String[] split = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 10)
                .map(tokenProvider::getTokenSubject)
                .orElse("anonymous:ANONYMOUS")
                .split(":");

        Account account = accountRepo.findAccountByNickNameAndUuid(split[0], split[1]).orElse(null);

        if (split[1].equals("ANONYMOUS") || account == null) {
            return new User("anonymous", "", List.of(new SimpleGrantedAuthority("ANONYMOUS")));
        }
        return new User(account.getUuid(), "", List.of(new SimpleGrantedAuthority(account.getType().name())));
    }

    private void reIssueAccessToken(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        try {
            String requestRefreshToken = parseBearerToken(request, TokenType.REFRESH);
            String requestAccessToken = parseBearerToken(request, TokenType.ACCESS);
            if (requestAccessToken != null && requestRefreshToken != null && tokenProvider.isTokenValid(requestRefreshToken)) {
                RefreshToken originRefreshToken = refreshTokenRepo.findRefreshTokenByAccessToken(requestAccessToken).orElse(null);
                if (originRefreshToken != null) {
                    Account account = accountRepo.findAccountByUuid(originRefreshToken.getId()).orElseThrow();
                    String newAccessToken = tokenProvider.createToken(String.format("%s:%s", account.getNickName(), account.getUuid()), TokenType.ACCESS);
                    String newRefreshToken = tokenProvider.createToken("", TokenType.REFRESH);

                    refreshTokenRepo.delete(originRefreshToken);
                    refreshTokenRepo.save(new RefreshToken(originRefreshToken.getId(), newRefreshToken, newAccessToken));

                    User user = parseUserSpecification(newAccessToken);
                    AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, newAccessToken, user.getAuthorities());
                    authenticated.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticated);

                    response.setHeader("New-Access-Token", newAccessToken);
                    response.setHeader("New-Refresh-Token", newRefreshToken);
                    log.info("reIssueAccessToken end");
                }
            } else {
                log.info("ex 1");
                request.setAttribute("exception", exception);
            }
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException e) {
            log.info("ex 2");
            request.setAttribute("exception", e);
        } catch (Exception e) {
            log.error("Error without JWT!!");
            log.error(e.toString());
            request.setAttribute("exception", e);
        }
    }
}
