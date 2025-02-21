package org.fourstack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class AppSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(requests ->
                    requests.requestMatchers("/api/v1/contact", "/api/v1/notices", "/welcome", "/error").permitAll()
                            .requestMatchers("/api/v1/account/**", "/api/v1/balance/**", "/api/v1/cards/**", "/api/v1/loan/**").authenticated())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails fourStackUser = getUser("fourStack", "{bcrypt}$2y$12$yfk5u81chWwb4ZI5nB3Lye6tSlXFv/H3j1ywVU5j3v8puhP1rt4Iq", "admin"); //BlueMonk@1563
    UserDetails user = getUser("user", "{noop}user@12345", "read");
    UserDetails admin = getUser("admin", "{bcrypt}$2y$12$/kPnytWzcLuiAgW/Hzgjq.IGlzWmylEdiOZnCC1gIyIgLxkdQEUHq", "admin"); //admin@987

    return new InMemoryUserDetailsManager(fourStackUser, user, admin);
  }

  private static UserDetails getUser(String username, String password, String... authorities) {
    return User.builder().username(username)
            .password(password)
            .authorities(authorities).build();
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
