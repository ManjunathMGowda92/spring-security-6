package org.fourstack.model;

import lombok.Data;

@Data
public class ContactMessageDetails {
  private String contactId;
  private String contactName;
  private String contactEmail;
  private String subject;
  private String message;
  private String createdDate;
}
