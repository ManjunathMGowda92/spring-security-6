package org.fourstack.controller;

import org.fourstack.model.LoginUserDetails;
import org.fourstack.model.UserDetails;
import org.fourstack.service.security.UserManagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<String> createUser(@RequestBody LoginUserDetails userDetails) {
    userAdminService.createNewUser(userDetails);
    return ResponseEntity.ok("Successfully created the user");
  }

  @DeleteMapping("/deleteUser/{email}")
  public ResponseEntity<String> removeUser(@PathVariable String email) {
    userAdminService.deleteUser(email);
    return ResponseEntity.ok("Successfully deleted the user : " + email);
  }

  @GetMapping("/user")
  public ResponseEntity<UserDetails> removeUser(Authentication authentication) {
    UserDetails details = userAdminService.retrieveLoggedInUserDetails(authentication.getName());
    return ResponseEntity.ok(details);
  }
}
