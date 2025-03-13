package org.fourstack.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.fourstack.constant.ApplicationConstant;
import org.fourstack.service.security.JwtTokenService;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenValidationFilter extends OncePerRequestFilter {
  private final JwtTokenService jwtTokenService;

  /**
   * @param request     Http Servlet Request object.
   * @param response    Http Servlet Response object.
   * @param filterChain FilterChain object.
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader(ApplicationConstant.JWT_HEADER);
    if (null != authHeader && authHeader.contains("Bearer ")) {
      try {
        Environment env = getEnvironment();
        String secret = env.getProperty(ApplicationConstant.JWT_SECRET_KEY, ApplicationConstant.DEFAULT_JWT_SECRET);
        String jwtToken = authHeader.substring(7);
        if (jwtTokenService.isTokenExpired(jwtToken, secret)) {
          throw new BadCredentialsException("Token expired");
        }
        Claims claims = jwtTokenService.extractClaims(jwtToken, secret);
        String username = jwtTokenService.extractUsername(claims);
        List<GrantedAuthority> grantedAuthorities = jwtTokenService.extractAuthorities(claims);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      } catch (Exception e) {
        throw new BadCredentialsException("Invalid token received!");
      }
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    // This Filter should not be executed for authenticate API.
    return request.getServletPath().equals("/api/v1/user/authenticate");
  }
}
