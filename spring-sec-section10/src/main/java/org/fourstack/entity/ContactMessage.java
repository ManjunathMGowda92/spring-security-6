package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact_message")
@Getter
@Setter
public class ContactMessage {
  @Id
  private String contactId;
  private String contactName;
  private String contactEmail;
  private String subject;
  private String message;
  private String createdTimeStamp;
}
