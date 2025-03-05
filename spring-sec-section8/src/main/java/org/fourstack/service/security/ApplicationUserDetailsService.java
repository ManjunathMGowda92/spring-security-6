package org.fourstack.service.security;

import lombok.RequiredArgsConstructor;
import org.fourstack.dao.CustomerInfoRepository;
import org.fourstack.entity.CustomerInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

  private final CustomerInfoRepository customerRepository;

  /**
   * @param username the username identifying the user whose data is required.
   * @return UserDetails object for authentication purpose
   * @throws UsernameNotFoundException throws if no data found for the provided username.
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<CustomerInfo> optionalCustomerInfo = customerRepository.findByEmailIgnoreCase(username);
    CustomerInfo customerInfo = optionalCustomerInfo.orElseThrow(() -> new UsernameNotFoundException("No user found for :" + username));
    return User.builder()
            .username(customerInfo.getEmail())
            .password(customerInfo.getPwd())
            .authorities(customerInfo.getRole())
            .build();
  }
}
