package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF (Cross-Site Request Forgery) protection
                .csrf(csrf -> csrf.disable())
                // Configure authorization for POST requests
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()  // Allow all POST requests
                        .anyRequest().permitAll()  // Allow all other requests
                );

        return http.build();
    }
}
