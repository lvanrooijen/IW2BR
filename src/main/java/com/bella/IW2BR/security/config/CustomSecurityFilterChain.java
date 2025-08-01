package com.bella.IW2BR.security.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.bella.IW2BR.security.jwt.JwtAuthFilter;
import com.bella.IW2BR.security.jwt.UnAuthorizedEntryPoint;
import com.bella.IW2BR.utils.constants.routes.SecurityRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class CustomSecurityFilterChain {
  private final JwtAuthFilter jwtAuthFilter;
  private final CorsConfig corsConfig;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfig.corsConfiguration()))
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(SecurityRoutes.getNonAuthenticatedEndpoints())
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .httpBasic(withDefaults());

    http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    http.exceptionHandling(
        configures -> configures.authenticationEntryPoint(new UnAuthorizedEntryPoint()));

    return http.build();
  }
}
