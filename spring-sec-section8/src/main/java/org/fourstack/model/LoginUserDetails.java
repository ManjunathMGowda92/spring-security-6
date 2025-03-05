package org.fourstack.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDetails {
  private String username;
  private String email;
  private String mobileNumber;
  private String pwd;
  private String role;
}
