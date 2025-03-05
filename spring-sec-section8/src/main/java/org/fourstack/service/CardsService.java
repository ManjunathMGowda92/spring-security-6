package org.fourstack.service;

import lombok.RequiredArgsConstructor;
import org.fourstack.dao.CardsRepository;
import org.fourstack.entity.Cards;
import org.fourstack.model.CardCreationDetails;
import org.fourstack.model.CardDetails;
import org.fourstack.util.KeyGenerationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CardsService {
  private final CardsRepository cardsRepository;

  public CardDetails createCardDetails(CardCreationDetails creationDetails) {
    Cards cards = convertToCard(creationDetails);
    Cards savedCard = cardsRepository.save(cards);
    return convertToDetails(savedCard);
  }

  private CardDetails convertToDetails(Cards card) {
    CardDetails details = new CardDetails();
    details.setCardId(card.getCardId());
    details.setCardNumber(card.getCardNumber());
    details.setCustomerId(card.getCustomerId());
    details.setCardType(card.getCardType());
    details.setTotalLimit(card.getTotalLimit());
    details.setAmountUsed(card.getAmountUsed());
    details.setAvailableAmount(card.getAvailableAmount());
    details.setCreatedTimeStamp(card.getCreatedTimeStamp());
    return details;
  }

  private Cards convertToCard(CardCreationDetails creationDetails) {
    Cards card = new Cards();
    card.setCardId(KeyGenerationUtil.generateUniqueKey());
    card.setCardNumber(card.getCardId());
    card.setCustomerId(creationDetails.getCustomerId());
    card.setCardType(creationDetails.getCardType());
    card.setTotalLimit(creationDetails.getTotalLimit());
    card.setAmountUsed(0.0);
    card.setAvailableAmount(card.getTotalLimit());
    card.setCreatedTimeStamp(LocalDateTime.now().toString());
    return card;
  }
}
