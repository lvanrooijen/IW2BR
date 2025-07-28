package com.bella.IW2BR.events.userregistration;


import com.bella.IW2BR.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationPublisher {
  private final ApplicationEventPublisher publisher;

  public void publishUserRegistrationEvent(final User user) {
    UserRegistrationEvent userRegistrationEvent =
        new UserRegistrationEvent(this, user.getEmail(), user.getId(), user.getFullName());

    publisher.publishEvent(userRegistrationEvent);
  }
}
