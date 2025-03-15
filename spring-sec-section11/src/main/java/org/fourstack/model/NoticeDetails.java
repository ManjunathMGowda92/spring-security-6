package org.fourstack.model;

import lombok.Data;

@Data
public class NoticeDetails {
  private String noticeId;
  private String noticeSummary;
  private String noticeDetailsInfo;
  private String createdDate;
  private String updatedDate;
}
