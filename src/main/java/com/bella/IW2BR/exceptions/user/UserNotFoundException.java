package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.base.BaseNotFoundException;

public class UserNotFoundException extends BaseNotFoundException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
