package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;
import org.fourstack.enums.AccountType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@Getter
@Setter
public class Accounts {
  @Id
  private String accountNum;
  private String customerId;
  private AccountType accountType;
  private String branchAddress;
  private String createdTimeStamp;
}

