package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * Should be thrown when a user with an invalid role is registered. extends {@link
 * BaseBadRequestException}
 *
 * <p>handled by {@link GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 */
public class InvalidUserRoleException extends BaseBadRequestException {
  public InvalidUserRoleException(String message) {
    super(message);
  }
}
