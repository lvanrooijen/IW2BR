package com.bella.IW2BR.exceptions.user;

public class FailedLoginException extends RuntimeException {
  public FailedLoginException(String message) {
    super(message);
  }
}
