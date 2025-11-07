package com.bella.IW2BR.exceptions.exam;

import com.bella.IW2BR.domain.environment.util.EnvironmentMember;
import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when the Environment of a {@link EnvironmentMember} has a null
 * value
 *
 * <p>This exception extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 *
 * <p>Returns HTTP status code {@code 400 BAD REQUEST}
 *
 * <p>The exception message is logged and returned to the client
 */
public class MissingEnvironmentException extends RuntimeException {
  public MissingEnvironmentException(String message) {
    super(message);
  }
}
