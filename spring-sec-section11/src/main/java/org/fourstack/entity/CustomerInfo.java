package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "customer")
@Getter
@Setter
public class CustomerInfo {
  @Id
  private String customerId;
  private String name;
  private String email;
  private String mobileNumber;
  private String pwd;
  private List<String> roles;
  private String createdTimeStamp;
}
