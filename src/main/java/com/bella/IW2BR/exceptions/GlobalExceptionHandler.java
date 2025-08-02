package com.bella.IW2BR.exceptions;

import com.bella.IW2BR.exceptions.authentication.InvalidRefreshTokenException;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;
import com.bella.IW2BR.exceptions.base.BaseForbiddenException;
import com.bella.IW2BR.exceptions.base.BaseNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.security.auth.login.FailedLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * Handles all exceptions that extend the {@link BaseNotFoundException} class
   *
   * @param e exception message
   * @return ProblemDetail with status code 404 not found and a detail containing the exception
   *     message
   */
  @ExceptionHandler(BaseNotFoundException.class)
  public ProblemDetail handleEntitiesNotFoundExceptions(Exception e) {
    String className = e.getClass().getSimpleName();
    log.warn(String.format("[%s] detail=%s", className, e.getMessage()));
    return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
  }

  /**
   * Handles all exceptions that extend the {@link BaseBadRequestException} class
   *
   * @param e exception message
   * @return ProblemDetail with status code 400 bad request and a detail containing the exception
   *     message
   */
  @ExceptionHandler(BaseBadRequestException.class)
  public ProblemDetail handleBadRequestExceptions(Exception e) {
    String className = e.getClass().getSimpleName();
    log.warn(String.format("[%s] detail=%s", className, e.getMessage()));
    return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  /**
   * Handles all exceptions that extend the {@link BaseForbiddenException} class
   *
   * @param e exception message
   * @return ProblemDetail with status code 403 forbidden, no message included.
   */
  @ExceptionHandler(BaseForbiddenException.class)
  public ProblemDetail handleForbiddenExceptions(Exception e) {
    String className = e.getClass().getSimpleName();
    log.warn(String.format("[%s] detail=%s", className, e.getMessage()));
    return ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
  }

  /** Fall back exception handler, handles all the uncaught exceptions */
  @ExceptionHandler(Exception.class)
  public ProblemDetail handleException(Exception e) {
    log.error("[FALLBACK] {}", e.getMessage(), e);
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred");
  }

  /**
   * Handles exceptions that are thrown validation fails
   *
   * @param e exception message
   * @return ProblemDetail including causes for failed validation
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleFailedValidation(MethodArgumentNotValidException e) {
    Map<String, String> errors =
        e.getBindingResult().getFieldErrors().stream()
            .collect(
                Collectors.toMap(
                    FieldError::getField,
                    FieldError::getDefaultMessage,
                    (existing, replacement) -> existing));
    String message = "Invalid Request Body";
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
    problemDetail.setProperty("errors", errors);
    return problemDetail;
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ProblemDetail handleHandlerMethodValidationException(HandlerMethodValidationException e) {
    log.warn("[HandlerMethodValidationException] detail: {}", e.getMessage(), e);
    List<Object> errors = Arrays.stream(e.getDetailMessageArguments()).toList();
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Query parameter");
    problemDetail.setProperty("errors", errors);
    return problemDetail;
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ProblemDetail handleMissingServletRequestParameterException(
      MissingServletRequestParameterException e) {
    log.warn("[MissingServletRequestParameterException] {}", e.getMessage(), e);
    return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ProblemDetail handleAccessDeniedException(Exception e) {
    log.warn("[AccessDeniedException] {}", e.getMessage(), e);
    return ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ProblemDetail handleHttpMessageNotReadableException(Exception e) {
    log.warn("[HttpMessageNotReadableException] {}", e.getMessage(), e);
    return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "invalid request body");
  }

  @ExceptionHandler(FailedLoginException.class)
  public ProblemDetail handleFailedLoginException(Exception e) {
    log.warn("[FailedLoginException] {}", e.getMessage(), e);
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.BAD_REQUEST, "Invalid username and/or password");
  }

  @ExceptionHandler(InvalidRefreshTokenException.class)
  public ProblemDetail handleInvalidRefreshTokenException(Exception e) {
    log.warn("[InvalidRefreshTokenException] {}", e.getMessage(), e);
    return ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
  }
}
