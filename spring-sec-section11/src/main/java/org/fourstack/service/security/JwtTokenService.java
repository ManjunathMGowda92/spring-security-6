package org.fourstack.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtTokenService {


  /**
   * Method to generate the JWT authentication token by using the valid authentication.
   *
   * @param authentication Authentication Object.
   * @param key            Secret Key used for generating the token.
   * @return String value of generated token.
   */
  public String generateToken(Authentication authentication, String key) {
    return Jwts.builder()
            .issuer("Easy Bank")
            .subject("JWT Token")
            .claim("username", authentication.getName())
            .claim("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(",")))
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
            .signWith(generateSecreteKey(key))
            .compact();
  }

  private SecretKey generateSecreteKey(String key) {
    byte[] keyBytes = Decoders.BASE64.decode(key);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public Claims extractClaims(String token, String key) {
    return Jwts.parser()
            .verifyWith(generateSecreteKey(key))
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  public String extractUsername(String token, String key) {
    return extractClaim(token, key, claim -> claim.get("username", String.class));
  }

  public String extractUsername(Claims claims) {
    return claims.get("username", String.class);
  }

  public List<GrantedAuthority> extractAuthorities(Claims claims) {
    String authorities = claims.get("authorities", String.class);
    return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
  }

  public <T> T extractClaim(String token, String key, Function<Claims, T> claimsResolver) {
    Claims claims = extractClaims(token, key);
    return claimsResolver.apply(claims);
  }

  public boolean isTokenExpired(String token, String key) {
    return extractExpiration(token, key).before(new Date());
  }

  private Date extractExpiration(String token, String key) {
    return extractClaim(token, key, Claims::getExpiration);
  }
}
