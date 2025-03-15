package org.fourstack.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class AuthoritiesLoggingFilter implements Filter {
  private static final Logger logger = LoggerFactory.getLogger(AuthoritiesLoggingFilter.class);

  /**
   * @param request  The request to process
   * @param response The response associated with the request
   * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
   *                 to for further processing
   * @throws IOException      throws IOException if any IO error
   * @throws ServletException throws ServletException if any servlet related error
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      logger.info("User : {} is successfully authenticated and authorities were : {}",
              authentication.getName(), authentication.getAuthorities());
    }
    chain.doFilter(request, response);
  }
}
