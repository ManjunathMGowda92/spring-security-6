package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDetails {
  private String username;
  private String password;
  private String role;
}
