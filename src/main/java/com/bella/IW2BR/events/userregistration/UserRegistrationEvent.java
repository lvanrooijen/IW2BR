package com.bella.IW2BR.events.userregistration;

import java.util.UUID;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserRegistrationEvent extends ApplicationEvent {
  private final String email;
  private final UUID userId;
  private final String fullName;

  public UserRegistrationEvent(Object source, String email, UUID userId, String fullName) {
    super(source);
    this.email = email;
    this.userId = userId;
    this.fullName = fullName;
  }
}
