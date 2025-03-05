package org.fourstack.service.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
          throws IOException, ServletException {
    var message = (accessDeniedException != null && accessDeniedException.getMessage() != null)
            ? accessDeniedException.getMessage() : "Authorization failed";
    String path = request.getRequestURI();
    response.setHeader("easy-bank-authorization", "Authorization-failure");
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");
    String jsonResponse = """
            {
              "timestamp" : %s,
              "status" : %d,
              "error" : %s,
              "message" : %s,
              "path": " %s
            }
            """.formatted(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
            message, path);
    response.getWriter().write(jsonResponse);
  }
}
