package com.bella.IW2BR.entities.user.dto;

public class UserConstraints {
  public static final int NAME_LENGTH_MIN = 3;
  public static final int FIRST_NAME_LENGTH_MAX = 50;
  public static final int LAST_NAME_LENGTH_MAX = 50;

  public static final String FIRST_NAME_INVALID_LENGTH =
      "First name must be between 3 and 50 characters";

  public static final String LAST_NAME_INVALID_LENGTH_MSG =
      "Last name must be between 3 and 100 characters";
}
