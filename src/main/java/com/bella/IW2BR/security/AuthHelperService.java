package com.bella.IW2BR.security;

import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.exceptions.generic.IllegalActionException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthHelperService {
  public User getAuthenticatedUser() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (user == null) {
      throw new IllegalActionException(
          "[AuthHelperService:getAuthenticatedUser] Authenticated user not found");
    }
    return user;
  }
}
