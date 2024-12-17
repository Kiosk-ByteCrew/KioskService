package com.kiosk.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for simplicity; enable it in production as needed
                .csrf(AbstractHttpConfigurer::disable)
                // Configure authorization for HTTP requests
                .authorizeHttpRequests(authz -> authz
                        // Permit all requests without authentication
                        .anyRequest().permitAll()
                )
                // Disable HTTP Basic Authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
