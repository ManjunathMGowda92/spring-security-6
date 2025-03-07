package org.fourstack.model;

import lombok.Data;
import org.fourstack.enums.CardType;

@Data
public class CardCreationDetails {
  private String customerId;
  private CardType cardType;
  private Double totalLimit;
}
