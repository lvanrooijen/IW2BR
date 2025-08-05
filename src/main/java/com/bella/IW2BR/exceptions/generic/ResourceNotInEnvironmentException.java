package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when the given environment and related resource are not connected
 * to each other.
 *
 * <p>(for example a Tag that is not part of the environment)
 *
 * <p>It extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 */
public class ResourceNotInEnvironmentException extends BaseBadRequestException {
  public ResourceNotInEnvironmentException(String message) {
    super(message);
  }
}
