package org.fourstack.controller;

import org.fourstack.dao.AppUserDetailsRepository;
import org.fourstack.entity.AppUserDetails;
import org.fourstack.entity.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class WelcomeController {

  @Autowired
  private AppUserDetailsRepository repository;

  @GetMapping("/welcome")
  public String getMessage() {
    return "WelCome to Spring Application without Security";
  }

  /*@PostMapping(value = "/insertUser", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String insertAppDetails(@RequestBody LoginUserDetails loginUserDetails) {
    Set<String> authorities = loginUserDetails.getAuthorities();
    AppUserDetails userDetails;
    if (authorities != null && !authorities.isEmpty()) {
      Set<GrantedAuthority> simpleGrantedAuthorities = authorities.stream()
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toSet());
       userDetails = new AppUserDetails(loginUserDetails.getUsername(), loginUserDetails.getPassword(),
              simpleGrantedAuthorities);
    } else {
      userDetails = new AppUserDetails(loginUserDetails.getUsername(), loginUserDetails.getPassword());
    }
    repository.save(userDetails);
    return "Record saved successfully";
  }*/
}
