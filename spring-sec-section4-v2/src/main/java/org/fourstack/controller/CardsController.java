package org.fourstack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsController {

  @GetMapping("/myCards")
  public String getCardDetails() {
    return "Here are the cards details from the DB";
  }
}
