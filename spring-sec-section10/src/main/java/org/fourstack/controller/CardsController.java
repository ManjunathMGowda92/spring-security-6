package org.fourstack.controller;

import lombok.RequiredArgsConstructor;
import org.fourstack.model.CardCreationDetails;
import org.fourstack.model.CardDetails;
import org.fourstack.service.CardsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardsController {
  private final CardsService cardsService;

  @GetMapping("/myCards")
  public String getCardDetails() {
    return "Here are the cards details from the DB";
  }

  @PostMapping
  public CardDetails createCard(@RequestBody CardCreationDetails details) {
    return cardsService.createCardDetails(details);
  }
}
