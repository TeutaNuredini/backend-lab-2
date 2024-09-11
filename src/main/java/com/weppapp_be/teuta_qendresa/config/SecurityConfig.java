package com.weppapp_be.teuta_qendresa.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(getNoAuthEndpoints())
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(sse -> sse.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private String[] getNoAuthEndpoints() {
        return new String[] {
                "api/v1/auth/login",
                "api/v1/auth/register",
                "api/v1/users",
                "api/v1/auth/refresh",
                "/v3/api-docs/**",
                "/v3/api-docs.yaml",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/api/group/**",
                "/api/locations/all",
                "/api/locations/{id}",
                "api/categories/all",
                "api/categories/{id}",
                "api/events/all",
                "api/events/{id}",
                "api/events/by-location/{id}",
                "api/events/by-category/{id}",
                "api/events/top-selling",
        };
    }


    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
