package org.fourstack.model;

import lombok.Data;

@Data
public class AuthenticationDetails {
  private String email;
  private String pwd;
}
