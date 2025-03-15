package org.fourstack.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public final class CustomAuthenticationUtil {
  private CustomAuthenticationUtil(){
  }

  public static String getUserName() {
    SecurityContext context = SecurityContextHolder.getContext();
    if (context != null) {
      Authentication authentication = context.getAuthentication();
      if (authentication != null) {
        return authentication.getName();
      }
    }
    return null;
  }

  public static Optional<Authentication> getAuthentication() {
    SecurityContext context = SecurityContextHolder.getContext();
    if (context != null) {
      return Optional.ofNullable(context.getAuthentication());
    }
    return Optional.empty();
  }
}
