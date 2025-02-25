package org.fourstack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  @Value("${spring.application.username}")
  private String user;

  @GetMapping("/welcome")
  public String getMessage() {
    return "Hello " + user + ", WelCome to Spring Application without Security";
  }
}
