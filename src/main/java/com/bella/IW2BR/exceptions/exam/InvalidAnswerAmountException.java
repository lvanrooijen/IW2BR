package com.bella.IW2BR.exceptions.exam;

import com.bella.IW2BR.domain.exam.question.Question;
import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when the amount of answers does not match {@link
 * Question#getAnswerAmount()} Question entity
 *
 * <p>This exception extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 *
 * <p>Returns HTTP status code {@code 400 BAD REQUEST}
 *
 * <p>The exception message is logged and returned to the client
 */
public class InvalidAnswerAmountException extends BaseBadRequestException {
  public InvalidAnswerAmountException(String message) {
    super(message);
  }
}
