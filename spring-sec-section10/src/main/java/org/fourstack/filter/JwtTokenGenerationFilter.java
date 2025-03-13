package org.fourstack.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.fourstack.constant.ApplicationConstant;
import org.fourstack.service.security.JwtTokenService;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerationFilter extends OncePerRequestFilter {
  private final JwtTokenService jwtTokenService;
  /**
   * @param request Http Servlet Request object.
   * @param response Http Servlet Response object.
   * @param filterChain FilterChain object.
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (null != authentication) {
      Environment env = getEnvironment();
      String secret = env.getProperty(ApplicationConstant.JWT_SECRET_KEY, ApplicationConstant.DEFAULT_JWT_SECRET);
      String jwtToken = jwtTokenService.generateToken(authentication, secret);
      response.setHeader(ApplicationConstant.JWT_HEADER, jwtToken);
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    // This Filter should be executed only when we want to generate the token using authenticate method.
    return !request.getServletPath().equals("/api/v1/user/authenticate");
  }
}
