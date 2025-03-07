package org.fourstack.controller;

import lombok.RequiredArgsConstructor;
import org.fourstack.entity.Accounts;
import org.fourstack.model.AccountCreationDetails;
import org.fourstack.model.AccountDetails;
import org.fourstack.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
  private final AccountsService accountsService;

  @GetMapping("/myAccount/{customerId}")
  public AccountDetails getAccountDetails(@PathVariable String customerId) {
    return accountsService.retrieveAccountByCustomerId(customerId);
  }

  @PostMapping
  public ResponseEntity<AccountDetails> createAccount(@RequestBody AccountCreationDetails accountDetails) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(accountsService.createNewAccount(accountDetails));
  }
}
