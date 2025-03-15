package org.fourstack.config;

import org.fourstack.service.security.CustomAccessDeniedHandler;
import org.fourstack.service.security.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@Profile("prod")
public class ProdAppSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession").maximumSessions(1).maxSessionsPreventsLogin(true))
            .requiresChannel(rcc -> rcc.anyRequest().requiresSecure()) // Accepts only https in Prod Environment
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(requests ->
                    requests.requestMatchers("/api/v1/contact", "/api/v1/notices", "/welcome", "/error", "/register").permitAll()
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
