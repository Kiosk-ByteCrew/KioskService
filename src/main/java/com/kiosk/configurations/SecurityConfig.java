package com.kiosk.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Log log log log");
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ws").permitAll()
                        .requestMatchers("/ws-sockjs").permitAll() // Permit WebSocket connections
                        .requestMatchers("/kiosk/api/**").permitAll() // Permit API access
                        .anyRequest().authenticated() // Secure all other requests
                );
        return http.build();
    }
}
