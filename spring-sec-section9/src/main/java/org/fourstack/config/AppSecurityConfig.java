package org.fourstack.config;

import org.fourstack.filter.AuthoritiesLoggingFilter;
import org.fourstack.filter.CsrfCookieFilter;
import org.fourstack.filter.RequestValidationFilter;
import org.fourstack.service.security.CustomAccessDeniedHandler;
import org.fourstack.service.security.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@Profile("!prod")
public class AppSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
    return http.securityContext(contextConfig -> contextConfig.requireExplicitSave(false))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
              CorsConfiguration cors = new CorsConfiguration();
              cors.setAllowedOrigins(List.of("http://localhost:4000", "http://localhost:8080"));
              cors.setAllowedMethods(Collections.singletonList("*"));
              cors.setAllowCredentials(true);
              cors.setAllowedHeaders(Collections.singletonList("*"));
              cors.setMaxAge(3600L);
              return cors;
            }))
            .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                    .ignoringRequestMatchers("/api/v1/contact", "/api/v1/notices", "/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new AuthoritiesLoggingFilter(), BasicAuthenticationFilter.class)
            .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
            .authorizeHttpRequests(requests ->
                    requests.requestMatchers("/api/v1/contact", "/api/v1/notices", "/welcome", "/error", "/register").permitAll()
                            .requestMatchers("/api/v1/account/myAccount/**").hasRole("ADMIN")
                            .anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())
            .httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()))
            .exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()))
            .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public CompromisedPasswordChecker passwordChecker() {
    return new HaveIBeenPwnedRestApiPasswordChecker();
  }
}
