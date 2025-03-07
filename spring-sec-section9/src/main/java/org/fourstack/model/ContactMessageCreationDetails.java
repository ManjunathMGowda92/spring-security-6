package org.fourstack.model;

import lombok.Data;

@Data
public class ContactMessageCreationDetails {
  private String contactName;
  private String contactEmail;
  private String subject;
  private String message;
}
