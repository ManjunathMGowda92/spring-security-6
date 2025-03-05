package org.fourstack.service.security;

import lombok.RequiredArgsConstructor;
import org.fourstack.dao.CustomerInfoRepository;
import org.fourstack.entity.CustomerInfo;
import org.fourstack.exception.NotFoundException;
import org.fourstack.model.LoginUserDetails;
import org.fourstack.exception.UserAlreadyExistException;
import org.fourstack.model.UserDetails;
import org.fourstack.util.KeyGenerationUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagingService {


  private final CustomerInfoRepository repository;
  private final PasswordEncoder passwordEncoder;

  public void createNewUser(LoginUserDetails userDetails) {
    if (!userExists(userDetails.getEmail())) {
      CustomerInfo appUserDetails = convertToUserDetails(userDetails);
      repository.save(appUserDetails);
    } else {
      throw new UserAlreadyExistException("User already exist for given details");
    }
  }

  private CustomerInfo convertToUserDetails(LoginUserDetails userDetails) {
    CustomerInfo info = new CustomerInfo();
    info.setCustomerId(KeyGenerationUtil.generateUniqueKey());
    info.setName(userDetails.getUsername());
    info.setEmail(userDetails.getEmail());
    info.setMobileNumber(userDetails.getMobileNumber());
    info.setPwd(passwordEncoder.encode(userDetails.getPwd()));
    info.setRole(userDetails.getRole().toUpperCase());
    info.setCreatedTimeStamp(LocalDateTime.now().toString());
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


  public void deleteUser(String email) {
    if (userExists(email)) {
      repository.deleteByEmailIgnoreCase(email);
    } else {
      throw new UsernameNotFoundException(email);
    }
  }

  public UserDetails retrieveLoggedInUserDetails(String email) {
    Optional<CustomerInfo> optionalUser = repository.findByEmailIgnoreCase(email);
    if (optionalUser.isPresent()) {
      return convertToUserDetails(optionalUser.get());
    }
    throw new NotFoundException("User does not exits for :" + email);
  }

  private UserDetails convertToUserDetails(CustomerInfo customerInfo) {
    UserDetails details = new UserDetails();
    details.setCustomerId(customerInfo.getCustomerId());
    details.setName(customerInfo.getName());
    details.setEmail(customerInfo.getEmail());
    details.setMobileNumber(customerInfo.getMobileNumber());
    details.setRole(customerInfo.getRole());
    return details;
  }

  private boolean userExists(String email) {
    Optional<CustomerInfo> optionalUser = repository.findByEmailIgnoreCase(email);
    return optionalUser.isPresent();
  }
}
