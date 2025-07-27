package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

public class UserAlreadyRegisteredException extends BaseBadRequestException {
  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
