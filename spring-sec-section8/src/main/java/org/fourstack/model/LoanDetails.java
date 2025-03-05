package org.fourstack.model;

import lombok.Data;
import org.fourstack.enums.LoanType;

@Data
public class LoanDetails {
  private String loanNumber;
  private String customerId;
  private String startDt;
  private LoanType loanType;
  private Double totalValue;
  private Double amountPaid;
  private Double outstandingAmount;
  private String createdTimeStamp;
}
