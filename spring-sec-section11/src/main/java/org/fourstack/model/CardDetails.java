package org.fourstack.model;

import lombok.Data;
import org.fourstack.enums.CardType;

@Data
public class CardDetails {
  private String cardId;
  private String cardNumber;
  private String customerId;
  private CardType cardType;
  private Double totalLimit;
  private Double amountUsed;
  private Double availableAmount;
  private String createdTimeStamp;
}
