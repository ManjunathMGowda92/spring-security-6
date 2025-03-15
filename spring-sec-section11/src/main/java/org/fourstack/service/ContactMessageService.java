package org.fourstack.service;

import lombok.RequiredArgsConstructor;
import org.fourstack.dao.ContactMessageRepository;
import org.fourstack.entity.ContactMessage;
import org.fourstack.model.ContactMessageCreationDetails;
import org.fourstack.model.ContactMessageDetails;
import org.fourstack.util.KeyGenerationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactMessageService {
  private final ContactMessageRepository contactMessageRepository;

  public ContactMessageDetails createContactMessage(ContactMessageCreationDetails details) {
    ContactMessage message = convertToContactMessage(details);
    ContactMessage savedMessage = contactMessageRepository.save(message);
    return convertToDetails(savedMessage);
  }

  public List<ContactMessageDetails> retrieveContactMessages() {
    return contactMessageRepository.findAll()
            .stream().map(this::convertToDetails)
            .toList();
  }

  private ContactMessageDetails convertToDetails(ContactMessage message) {
    ContactMessageDetails details = new ContactMessageDetails();
    details.setMessage(message.getMessage());
    details.setContactId(message.getContactId());
    details.setContactEmail(message.getContactEmail());
    details.setSubject(message.getSubject());
    details.setContactName(message.getContactName());
    details.setCreatedDate(message.getCreatedTimeStamp());
    return details;
  }

  private ContactMessage convertToContactMessage(ContactMessageCreationDetails details) {
    ContactMessage message = new ContactMessage();
    message.setContactId(KeyGenerationUtil.generateUniqueKey());
    message.setMessage(details.getMessage());
    message.setContactEmail(details.getContactEmail());
    message.setContactName(details.getContactName());
    message.setSubject(details.getSubject());
    message.setCreatedTimeStamp(LocalDateTime.now().toString());
    return message;
  }
}
