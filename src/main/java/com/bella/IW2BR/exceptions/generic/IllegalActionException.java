package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseForbiddenException;

/**
 * This exception should be thrown when an illegal action is performed.
 *
 * <p>This exception extends {@link BaseForbiddenException} and is handled by {@link
 * GlobalExceptionHandler#handleForbiddenExceptions(Exception)}
 *
 * <p>Returns HTTP status code {@code 403 FORBIDDEN}
 *
 * <p>The exception message will be logged but NOT returned to client
 */
public class IllegalActionException extends BaseForbiddenException {
  public IllegalActionException(String message) {
    super(message);
  }
}
