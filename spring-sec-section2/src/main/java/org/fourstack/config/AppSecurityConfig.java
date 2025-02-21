package org.fourstack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(requests ->
                    requests.requestMatchers("/api/v1/contact", "/api/v1/notices", "/welcome").permitAll()
                            .requestMatchers("/api/v1/account/**", "/api/v1/balance/**", "/api/v1/cards/**", "/api/v1/loan/**").authenticated())
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(Customizer.withDefaults())
            .build();
  }
}
