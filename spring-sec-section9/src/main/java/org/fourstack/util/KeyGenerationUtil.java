package org.fourstack.util;

import java.util.UUID;

public final class KeyGenerationUtil {
  private KeyGenerationUtil() {
  }

  public static String generateUniqueKey() {
    return UUID.randomUUID().toString();
  }
}
