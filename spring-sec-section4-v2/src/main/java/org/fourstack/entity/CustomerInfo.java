package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@Getter
@Setter
public class CustomerInfo {

  private String email;
  private String pwd;
  private String role;
}
