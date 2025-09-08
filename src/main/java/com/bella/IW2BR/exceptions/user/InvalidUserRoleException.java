package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when a user with an invalid role is registered.
 *
 * <p>This exception extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 *
 * <p>Returns HTTP status code {@code 400 BAD REQUEST}
 *
 * <p>The exception message will be logged and returned to the client
 */
public class InvalidUserRoleException extends BaseBadRequestException {
  public InvalidUserRoleException(String message) {
    super(message);
  }
}
