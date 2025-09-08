package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * Should be thrown when a user being registered already exists
 *
 * <p>This exception extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 *
 * <p>Returns HTTP status code {@code 400 BAD REQUEST}
 *
 * <p>The exception message will be logged and returned to the client
 */
public class UserAlreadyRegisteredException extends BaseBadRequestException {
  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
