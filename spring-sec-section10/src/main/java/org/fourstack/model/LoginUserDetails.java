package org.fourstack.model;

import lombok.Getter;
import lombok.Setter;
import org.fourstack.enums.UserRole;

import java.util.List;

@Getter
@Setter
public class LoginUserDetails {
  private String username;
  private String email;
  private String mobileNumber;
  private String pwd;
  private List<UserRole> roles;
}
