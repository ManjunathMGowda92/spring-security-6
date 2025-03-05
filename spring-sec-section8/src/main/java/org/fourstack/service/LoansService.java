package org.fourstack.service;

import lombok.RequiredArgsConstructor;
import org.fourstack.dao.LoansRepository;
import org.fourstack.entity.Loans;
import org.fourstack.model.LoanCreationDetails;
import org.fourstack.model.LoanDetails;
import org.fourstack.util.KeyGenerationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoansService {
  private final LoansRepository loansRepository;

  public LoanDetails createLoan(LoanCreationDetails loanDetails) {
    Loans loans = convertToLoan(loanDetails);
    Loans savedLoan = loansRepository.save(loans);
    return convertToDetails(savedLoan);
  }

  private LoanDetails convertToDetails(Loans loan) {
    LoanDetails details = new LoanDetails();
    details.setLoanNumber(loan.getLoanNumber());
    details.setCustomerId(loan.getCustomerId());
    details.setStartDt(loan.getStartDt());
    details.setLoanType(loan.getLoanType());
    details.setTotalValue(loan.getTotalValue());
    details.setAmountPaid(loan.getAmountPaid());
    details.setOutstandingAmount(loan.getOutstandingAmount());
    details.setCreatedTimeStamp(loan.getCreatedTimeStamp());
    return details;
  }

  private Loans convertToLoan(LoanCreationDetails loanDetails) {
    Loans loan = new Loans();
    loan.setLoanNumber(KeyGenerationUtil.generateUniqueKey());
    loan.setCustomerId(loanDetails.getCustomerId());
    loan.setStartDt(LocalDate.now().toString());
    loan.setLoanType(loanDetails.getLoanType());
    loan.setTotalValue(loanDetails.getTotalValue());
    loan.setAmountPaid(0.0);
    loan.setOutstandingAmount(loan.getTotalValue());
    loan.setCreatedTimeStamp(LocalDateTime.now().toString());
    return loan;
  }
}
