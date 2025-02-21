package org.fourstack.service;

import org.fourstack.dao.AppUserDetailsRepository;
import org.fourstack.entity.AppUserDetails;
import org.fourstack.entity.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAdminService implements UserDetailsService {

  @Autowired
  private AppUserDetailsRepository repository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public void createNewUser(LoginUserDetails userDetails) {
    if (!userExists(userDetails.getUsername())) {
      AppUserDetails appUserDetails = convertToUserDetails(userDetails);
      repository.save(appUserDetails);
    } else {
      throw new IllegalStateException("User already exist");
    }
  }

  private AppUserDetails convertToUserDetails(LoginUserDetails userDetails) {
    AppUserDetails details = new AppUserDetails(userDetails.getUsername(), passwordEncoder.encode(userDetails.getPassword()));
    Set<String> authorities = userDetails.getAuthorities();
    if (authorities != null && !authorities.isEmpty()) {
      Set<GrantedAuthority> grantedAuthorities = authorities.stream()
              .map(authority -> new SimpleGrantedAuthority(authority.toUpperCase(Locale.ROOT)))
              .collect(Collectors.toSet());
      details.setAuthorities(grantedAuthorities);
    }
    return details;
  }


  public void updateUser(LoginUserDetails user) {
    if (userExists(user.getUsername())) {
      AppUserDetails appUserDetails = convertToUserDetails(user);
      repository.save(appUserDetails);
    } else {
      throw new UsernameNotFoundException(user.getUsername());
    }
  }


  public void deleteUser(String username) {
    if (userExists(username)) {
      repository.deleteByUsernameIgnoreCase(username);
    } else {
      throw new UsernameNotFoundException(username);
    }
  }

  public boolean userExists(String username) {
    Optional<AppUserDetails> optionalUser = repository.findByUsernameIgnoreCase(username);
    return optionalUser.isPresent();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsernameIgnoreCase(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
