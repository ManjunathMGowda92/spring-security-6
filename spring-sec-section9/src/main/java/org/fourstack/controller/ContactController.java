package org.fourstack.controller;

import lombok.RequiredArgsConstructor;
import org.fourstack.entity.ContactMessage;
import org.fourstack.model.ContactMessageCreationDetails;
import org.fourstack.model.ContactMessageDetails;
import org.fourstack.service.ContactMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
private final ContactMessageService contactMessageService;
  @GetMapping
  public List<ContactMessageDetails> getContactMessages() {
    return contactMessageService.retrieveContactMessages();
  }

  @PostMapping
  public ContactMessageDetails createContactMessage(@RequestBody ContactMessageCreationDetails details) {
    return contactMessageService.createContactMessage(details);
  }
}
