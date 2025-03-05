package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;
import org.fourstack.enums.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_transaction")
@Getter
@Setter
public class AccountTransaction {
  @Id
  private String transactionId;
  private String accountNumber;
  private String customerId;
  private String transactionDt;
  private String transactionSummary;
  private TransactionType transactionType;
  private Double transactionAmount;
  private Double closingBalance;
  private String createdTimeStamp;
}
