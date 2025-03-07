package org.fourstack.enums;

public enum UserRole {
  USER, ADMIN, MANAGER;

  public static String getDefaultRole() {
    return USER.name();
  }
}
