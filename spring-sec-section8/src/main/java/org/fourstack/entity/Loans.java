package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;
import org.fourstack.enums.LoanType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loans")
@Getter
@Setter
public class Loans {
  @Id
  private String loanNumber;
  private String customerId;
  private String startDt;
  private LoanType loanType;
  private Double totalValue;
  private Double amountPaid;
  private Double outstandingAmount;
  private String createdTimeStamp;
}
