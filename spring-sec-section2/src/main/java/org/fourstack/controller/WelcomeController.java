package org.fourstack.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  @GetMapping("/welcome")
  public String getMessage() {
    return "WelCome to Spring Application without Security";
  }
}
