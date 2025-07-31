package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseNotFoundException;

/**
 * This exception should be thrown when an item/item is not found in attempting to retrieve it. It
 * extends {@link BaseNotFoundException} and is handled by {@link
 * GlobalExceptionHandler#handleEntitiesNotFoundExceptions(Exception)}
 */
public class ItemNotFoundException extends BaseNotFoundException {
  public ItemNotFoundException(String message) {
    super(message);
  }
}
