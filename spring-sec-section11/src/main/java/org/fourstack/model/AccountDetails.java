package org.fourstack.model;

import lombok.Data;
import org.fourstack.enums.AccountType;

@Data
public class AccountDetails {
  private String accountNum;
  private String customerId;
  private AccountType accountType;
  private String branchAddress;
  private String createdTimeStamp;
}
