package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseNotFoundException;

/**
 * This exception should be thrown when an item/item is not found in attempting to retrieve it.
 *
 * <p>This exception extends {@link BaseNotFoundException} and is handled by {@link
 * GlobalExceptionHandler#handleEntitiesNotFoundExceptions(Exception)}
 *
 * <p>Returns HTTP status code {@code 404 NOT FOUND}
 *
 * <p>The exception message will be logged and returned to the client
 */
public class ItemNotFoundException extends BaseNotFoundException {
  public ItemNotFoundException(String message) {
    super(message);
  }
}
