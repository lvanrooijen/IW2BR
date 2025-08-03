package com.bella.IW2BR.domain.environment.dto;

public class EnvironmentConstraints {
  public static final int TITLE_MIN_LENGTH = 3;
  public static final int TITLE_MAX_LENGTH = 50;
  public static final String INVALID_TITLE_LENGTH_MSG =
      "Environment title must be between "
          + TITLE_MIN_LENGTH
          + "and "
          + TITLE_MAX_LENGTH
          + " characters.";

  public static final int DESCRIPTION_MIN_LENGTH = 3;
  public static final int DESCRIPTION_MAX_LENGTH = 50;
  public static final String INVALID_DESCRIPTION_LENGTH_MSG =
      "Environment description must be between "
          + DESCRIPTION_MIN_LENGTH
          + "and "
          + DESCRIPTION_MAX_LENGTH
          + " characters.";
}
