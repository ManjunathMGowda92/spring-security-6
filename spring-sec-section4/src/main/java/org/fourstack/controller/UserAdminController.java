package org.fourstack.controller;

import org.fourstack.entity.LoginUserDetails;
import org.fourstack.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAdminController {
  private final UserAdminService userAdminService;

  @Autowired
  public UserAdminController(UserAdminService userAdminService) {
    this.userAdminService = userAdminService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@RequestBody LoginUserDetails userDetails) {
    userAdminService.createNewUser(userDetails);
    return ResponseEntity.ok("Successfully created the user");
  }
}
