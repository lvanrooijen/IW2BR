package com.bella.IW2BR.events.userregistration;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationPublisher {
  private final ApplicationEventPublisher publisher;

  public void publishUserRegistrationEvent(
      final String email, final UUID userId, final String fullName) {
    UserRegistrationEvent userRegistrationEvent =
        new UserRegistrationEvent(this, email, userId, fullName);

    publisher.publishEvent(userRegistrationEvent);
  }
}
