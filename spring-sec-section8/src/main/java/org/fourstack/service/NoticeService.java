package org.fourstack.service;

import lombok.RequiredArgsConstructor;
import org.fourstack.dao.NoticesRepository;
import org.fourstack.entity.Notices;
import org.fourstack.model.NoticeCreationDetails;
import org.fourstack.model.NoticeDetails;
import org.fourstack.util.KeyGenerationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

  private final NoticesRepository noticesRepository;

  public NoticeDetails createNotice(NoticeCreationDetails details) {
    Notices notices = convertToNotice(details);
    Notices savedNotice = noticesRepository.save(notices);
    return convertToDetails(savedNotice);
  }

  public List<NoticeDetails> retrieveNotices() {
    return noticesRepository.findAll()
            .stream()
            .map(this::convertToDetails)
            .toList();
  }

  private NoticeDetails convertToDetails(Notices notice) {
    NoticeDetails details = new NoticeDetails();
    details.setNoticeId(notice.getNoticeId());
    details.setNoticeSummary(notice.getNoticeSummary());
    details.setNoticeDetailsInfo(notice.getNoticeDetails());
    details.setCreatedDate(notice.getCreatedTimeStamp());
    details.setUpdatedDate(notice.getLastModifiedTimeStamp());
    return details;
  }

  private Notices convertToNotice(NoticeCreationDetails details) {
    Notices notices = new Notices();
    notices.setNoticeDetails(details.getNoticeDetailsInfo());
    notices.setNoticeSummary(details.getNoticeSummary());
    notices.setNoticeId(KeyGenerationUtil.generateUniqueKey());
    notices.setCreatedTimeStamp(LocalDateTime.now().toString());
    return notices;
  }
}
