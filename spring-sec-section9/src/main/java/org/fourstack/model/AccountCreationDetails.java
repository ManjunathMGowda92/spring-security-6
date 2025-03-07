package org.fourstack.model;

import lombok.Data;
import org.fourstack.enums.AccountType;

@Data
public class AccountCreationDetails {
  private String customerId;
  private AccountType accountType;
  private String branchAddress;
}
