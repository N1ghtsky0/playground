package kim.jiwook.playground.configuration;

import kim.jiwook.playground.Entity.Account;
import kim.jiwook.playground.configuration.exception.CustomException;
import kim.jiwook.playground.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static kim.jiwook.playground.configuration.exception.ErrorCode.INVALID_ACCOUNT_INFO;

@RequiredArgsConstructor
@Component
public class JsonWebTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JsonWebTokenProvider tokenProvider;
    private final AccountRepo accountRepo;
    private final String ACCESS_TOKEN_NAME = "jwt";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie token = parseBearerToken(request, ACCESS_TOKEN_NAME);
        if (token != null) {
            User user = parseUserSpecification(token.getValue());
            AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, token, user.getAuthorities());
            authenticated.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }

        filterChain.doFilter(request, response);
    }

    private Cookie parseBearerToken(HttpServletRequest request, String name) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> cookie.getName().equals(name))
                        .findFirst())
                .orElse(null);
    }

    private User parseUserSpecification(String token) {
        String userUuid = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 10)
                .map(tokenProvider::validateTokenAndGetSubject)
                .orElse("anonymous");

        if (userUuid.equals("anonymous")) {
            return new User(userUuid, "", Collections.singletonList(new SimpleGrantedAuthority("ANONYMOUS")));
        }

        Account account = accountRepo.findAccountByUuid(userUuid)
                .orElseThrow(() -> new CustomException(INVALID_ACCOUNT_INFO));

        return new User(userUuid, "", Collections.singletonList(new SimpleGrantedAuthority(account.getType().getRole())));
    }
}
