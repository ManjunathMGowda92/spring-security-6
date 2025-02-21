package org.fourstack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

  @GetMapping
  public String saveContactDetails() {
    return "Details were saved into the DB";
  }
}
