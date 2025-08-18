package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when a Query Parameter (RequestParam) is provided that is
 * invalid.
 *
 * <p>This Exception extends the {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 */
public class InvalidRequestParamException extends BaseBadRequestException {
  public InvalidRequestParamException(String message) {
    super(message);
  }
}
