package org.fourstack.service;

import lombok.RequiredArgsConstructor;
import org.fourstack.dao.AccountsRepository;
import org.fourstack.entity.Accounts;
import org.fourstack.exception.NotFoundException;
import org.fourstack.model.AccountCreationDetails;
import org.fourstack.model.AccountDetails;
import org.fourstack.util.KeyGenerationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountsService {

  private final AccountsRepository accountsRepository;

  public AccountDetails createNewAccount(AccountCreationDetails details) {
    Accounts accounts = convertToAccount(details);
    Accounts savedAccount = accountsRepository.save(accounts);
    return convertToAccountDetails(savedAccount);
  }

  public AccountDetails retrieveAccountByCustomerId(String customerId) {
    Optional<Accounts> optionalAccount = accountsRepository.findByCustomerId(customerId);
    if (optionalAccount.isPresent()) {
      return convertToAccountDetails(optionalAccount.get());
    } else {
      throw new NotFoundException("Account not found for CustomerId: " + customerId);
    }
  }

  private Accounts convertToAccount(AccountCreationDetails details) {
    Accounts accounts = new Accounts();
    accounts.setAccountNum(KeyGenerationUtil.generateUniqueKey());
    accounts.setAccountType(details.getAccountType());
    accounts.setBranchAddress(details.getBranchAddress());
    accounts.setCustomerId(details.getCustomerId());
    accounts.setCreatedTimeStamp(LocalDateTime.now().toString());
    return accounts;
  }

  private AccountDetails convertToAccountDetails(Accounts account) {
    AccountDetails details = new AccountDetails();
    details.setAccountNum(KeyGenerationUtil.generateUniqueKey());
    details.setAccountType(account.getAccountType());
    details.setBranchAddress(account.getBranchAddress());
    details.setCustomerId(account.getCustomerId());
    details.setAccountNum(account.getAccountNum());
    details.setCreatedTimeStamp(account.getCreatedTimeStamp());
    return details;
  }
}
