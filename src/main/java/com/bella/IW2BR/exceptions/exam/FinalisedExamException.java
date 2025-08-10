package com.bella.IW2BR.exceptions.exam;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when an attempt to edit an exam or exam related question is made
 * after the exam is finalised. This is not allowed because the exam results are dependent on the
 * exam.
 *
 * <p>This class extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 */
public class FinalisedExamException extends BaseBadRequestException {
  public FinalisedExamException(String message) {
    super(message);
  }
}
