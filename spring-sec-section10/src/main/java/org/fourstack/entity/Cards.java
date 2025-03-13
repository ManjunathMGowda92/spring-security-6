package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;
import org.fourstack.enums.CardType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
@Getter
@Setter
public class Cards {
  @Id
  private String cardId;
  private String cardNumber;
  private String customerId;
  private CardType cardType;
  private Double totalLimit;
  private Double amountUsed;
  private Double availableAmount;
  private String createdTimeStamp;
}
