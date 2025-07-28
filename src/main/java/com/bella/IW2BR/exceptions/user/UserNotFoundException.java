package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseNotFoundException;

/**
 * Should be thrown when a user is not found, extends {@link BaseNotFoundException}
 *
 * <p>Handled by: {@link GlobalExceptionHandler#handleEntitiesNotFoundExceptions(Exception)}
 */
public class UserNotFoundException extends BaseNotFoundException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
