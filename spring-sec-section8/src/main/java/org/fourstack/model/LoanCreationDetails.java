package org.fourstack.model;

import lombok.Data;
import org.fourstack.enums.LoanType;

@Data
public class LoanCreationDetails {
  private String customerId;
  private LoanType loanType;
  private Double totalValue;
}
