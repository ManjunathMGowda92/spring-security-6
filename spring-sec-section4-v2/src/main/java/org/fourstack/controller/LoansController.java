package org.fourstack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loan")
public class LoansController {

  @GetMapping("/myLoans")
  public String getLoanDetails() {
    return "Here are the loans details from the DB";
  }
}
