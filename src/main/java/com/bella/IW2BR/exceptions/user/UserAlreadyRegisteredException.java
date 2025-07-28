package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * Should be thrown when a user being registered already exists, extends {@link
 * BaseBadRequestException}
 *
 * <p>handled by {@link GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 */
public class UserAlreadyRegisteredException extends BaseBadRequestException {
  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
