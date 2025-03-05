package org.fourstack.controller;

import lombok.RequiredArgsConstructor;
import org.fourstack.model.NoticeCreationDetails;
import org.fourstack.model.NoticeDetails;
import org.fourstack.service.NoticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
public class NoticesController {
  private final NoticeService noticeService;

  @GetMapping
  public List<NoticeDetails> getNotices() {
    return noticeService.retrieveNotices();
  }

  @PostMapping
  public NoticeDetails createNotice(@RequestBody NoticeCreationDetails details) {
    return noticeService.createNotice(details);
  }
}
