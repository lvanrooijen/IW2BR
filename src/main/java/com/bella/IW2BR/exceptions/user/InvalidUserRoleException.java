package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

public class InvalidUserRoleException extends BaseBadRequestException {
  public InvalidUserRoleException(String message) {
    super(message);
  }
}
