package org.fourstack.service.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fourstack.exception.ErrorDetails;
import org.fourstack.util.ApplicationUtil;
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
    ErrorDetails details = ErrorDetails.builder()
            .errorMessage(message)
            .uriPath(path)
            .statusCode(HttpStatus.FORBIDDEN.value())
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.FORBIDDEN)
            .build();
    String jsonString = ApplicationUtil.convertToString(details);
    response.getWriter().write(jsonString);
  }
}
