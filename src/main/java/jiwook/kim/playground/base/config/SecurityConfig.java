package jiwook.kim.playground.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final String[] ALLOWED_URL_PERMIT_ALL = {"/", "/error", "/api/join", "/api/login"};
    private final String[] ALLOWED_URL_CSRF = {"/api/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrfConfigurer -> csrfConfigurer.ignoringRequestMatchers(ALLOWED_URL_CSRF))
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers(ALLOWED_URL_PERMIT_ALL).permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
