package org.fourstack.model;

import lombok.Data;

@Data
public class UserDetails {
  private String customerId;
  private String name;
  private String email;
  private String mobileNumber;
  private String role;
}
