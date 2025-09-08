package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseNotFoundException;

/**
 * Should be thrown when a user is not found
 *
 * <p>This exception extends {@link BaseNotFoundException} and is handled by: {@link
 * GlobalExceptionHandler#handleEntitiesNotFoundExceptions(Exception)}
 *
 * <p>Return HTTP status code {@code 404 NOT FOUND}
 *
 * <p>The exception message will be logged and returned to the client
 */
public class UserNotFoundException extends BaseNotFoundException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
