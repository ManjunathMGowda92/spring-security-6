package org.fourstack.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notice_details")
@Getter
@Setter
public class Notices {
  @Id
  private String noticeId;
  private String noticeSummary;
  private String noticeDetails;
  private String createdTimeStamp;
  private String lastModifiedTimeStamp;
}
