package com.bella.IW2BR.utils.constants;

public abstract class GlobalValidationConstraints {
  public static final int TITLE_MIN_LENGTH = 3;
  public static final int TITLE_MAX_LENGTH = 50;
  public static final String INVALID_TITLE_LENGTH_MSG =
      "Title must be between " + TITLE_MIN_LENGTH + "and " + TITLE_MAX_LENGTH + " characters.";

  public static final int DESCRIPTION_MIN_LENGTH = 3;
  public static final int DESCRIPTION_MAX_LENGTH = 50;
  public static final String INVALID_DESCRIPTION_LENGTH_MSG =
      "Description must be between "
          + DESCRIPTION_MIN_LENGTH
          + "and "
          + DESCRIPTION_MAX_LENGTH
          + " characters.";
}
