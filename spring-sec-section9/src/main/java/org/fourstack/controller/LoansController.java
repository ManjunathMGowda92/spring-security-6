package org.fourstack.controller;

import lombok.RequiredArgsConstructor;
import org.fourstack.model.LoanCreationDetails;
import org.fourstack.model.LoanDetails;
import org.fourstack.service.LoansService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
public class LoansController {
  private final LoansService loansService;

  @GetMapping("/myLoans")
  public String getLoanDetails() {
    return "Here are the loans details from the DB";
  }

  @PostMapping
  public LoanDetails createLoan(@RequestBody LoanCreationDetails details) {
    return loansService.createLoan(details);
  }
}
