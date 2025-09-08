package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when a Query Parameter (RequestParam) is provided that is
 * invalid.
 *
 * <p>This Exception extends the {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 *
 * <p>Returns HTTP status code {@code 400 BAD REQUEST}
 *
 * <p>The exception message is logged and returned to the client
 */
public class InvalidRequestParamException extends BaseBadRequestException {
  public InvalidRequestParamException(String message) {
    super(message);
  }
}
