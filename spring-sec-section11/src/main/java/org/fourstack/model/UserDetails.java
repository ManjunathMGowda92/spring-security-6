package org.fourstack.model;

import lombok.Data;

import java.util.List;

@Data
public class UserDetails {
  private String customerId;
  private String name;
  private String email;
  private String mobileNumber;
  private List<String> roles;
}
