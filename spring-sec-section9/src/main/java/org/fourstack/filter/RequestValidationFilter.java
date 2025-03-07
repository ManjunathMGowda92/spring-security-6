package org.fourstack.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidationFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    if (request instanceof HttpServletRequest req && response instanceof HttpServletResponse res) {
      String header = req.getHeader(HttpHeaders.AUTHORIZATION);
      if (null != header) {
        header = header.trim();
        if (StringUtils.startsWithIgnoreCase(header, "Basic ")) {
          byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
          try {
            byte[] decoded = Base64.getDecoder().decode(base64Token);
            String token = new String(decoded, StandardCharsets.UTF_8);
            int tokenIndex = token.indexOf(":");
            if (tokenIndex == -1) {
              throw new BadCredentialsException("Invalid basic authentication token");
            }
            String email = token.substring(0, tokenIndex);
            if (email.toLowerCase().contains("test")) {
              res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
              return;
            }
          } catch (Exception e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
          }
        }
      }
    }
    chain.doFilter(request, response);
  }
}
