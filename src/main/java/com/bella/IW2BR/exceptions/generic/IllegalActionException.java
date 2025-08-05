package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseForbiddenException;

/**
 * This exception should be thrown when an illegal action is performed. It extends {@link
 * BaseForbiddenException} and is handled by {@link
 * GlobalExceptionHandler#handleForbiddenExceptions(Exception)}
 */
public class IllegalActionException extends BaseForbiddenException {
  public IllegalActionException(String message) {
    super(message);
  }
}
