package org.fourstack.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationEvents {
  private static final Logger logger = LoggerFactory.getLogger(AuthorizationEvents.class);

  @EventListener
  public void onFailure(AuthorizationDeniedEvent deniedEvent) {
    boolean accessGranted = deniedEvent.getAuthorizationResult().isGranted();
    logger.error("Authorization failed for the user : {} with access granted : {}, due to: {}",
            deniedEvent.getAuthentication().get().getName(), accessGranted,
            deniedEvent.getAuthorizationResult().toString());
  }
}
