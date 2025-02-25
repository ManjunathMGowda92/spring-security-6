package org.fourstack.controller;

import org.fourstack.entity.LoginUserDetails;
import org.fourstack.service.UserManagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAdminController {
  private final UserManagingService userAdminService;

  @Autowired
  public UserAdminController(UserManagingService userAdminService) {
    this.userAdminService = userAdminService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@RequestBody LoginUserDetails userDetails) {
    userAdminService.createNewUser(userDetails);
    return ResponseEntity.ok("Successfully created the user");
  }

  @DeleteMapping("/deleteUser/{username}")
  public ResponseEntity<?> removeUser(@PathVariable String username) {
    userAdminService.deleteUser(username);
    return ResponseEntity.ok("Successfully deleted the user : " + username);
  }
}
