package org.fourstack.service;

import lombok.RequiredArgsConstructor;
import org.fourstack.dao.CustomerInfoRepository;
import org.fourstack.entity.CustomerInfo;
import org.fourstack.entity.LoginUserDetails;
import org.fourstack.exception.UserAlreadyExistException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagingService {


  private final CustomerInfoRepository repository;
  private final PasswordEncoder passwordEncoder;

  public void createNewUser(LoginUserDetails userDetails) {
    if (!userExists(userDetails.getUsername())) {
      CustomerInfo appUserDetails = convertToUserDetails(userDetails);
      repository.save(appUserDetails);
    } else {
      throw new UserAlreadyExistException("User already exist for given details");
    }
  }

  private CustomerInfo convertToUserDetails(LoginUserDetails userDetails) {
    CustomerInfo info = new CustomerInfo();
    info.setEmail(userDetails.getUsername());
    info.setPwd(passwordEncoder.encode(userDetails.getPassword()));
    info.setRole(userDetails.getRole());
    return info;
  }


  public void updateUser(LoginUserDetails user) {
    if (userExists(user.getUsername())) {
      CustomerInfo customerInfo = convertToUserDetails(user);
      repository.save(customerInfo);
    } else {
      throw new UsernameNotFoundException(user.getUsername());
    }
  }


  public void deleteUser(String username) {
    if (userExists(username)) {
      repository.deleteByEmailIgnoreCase(username);
    } else {
      throw new UsernameNotFoundException(username);
    }
  }

  public boolean userExists(String username) {
    Optional<CustomerInfo> optionalUser = repository.findByEmailIgnoreCase(username);
    return optionalUser.isPresent();
  }
}
