package com.jiwook.playground.config;

import com.jiwook.playground.model.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final String[] ALL_ALLOWED_URLS = {"/", "/index", "/login", "/join"};
    private final String[] USER_ALLOWED_URLS = {"/info"};
    private final String[] ADMIN_ALLOWED_URLS = {"/users"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(ALL_ALLOWED_URLS).permitAll()
                        .requestMatchers(USER_ALLOWED_URLS).hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name())
                        .requestMatchers(ADMIN_ALLOWED_URLS).hasAuthority(UserRole.ADMIN.name()));
        return http.build();
    }
}
