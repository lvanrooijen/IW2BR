package com.bella.IW2BR.utils.constants;

/**
 * Super class for Validation constraints.
 *
 * <p>Contains Generic Constraints
 */
public abstract class GlobalValidationConstraints {
  public static final int TITLE_MIN = 3;
  public static final int TITLE_MAX = 30;
  public static final String INVALID_TITLE_LENGTH_MSG =
      "Title must be between " + TITLE_MIN + "and " + TITLE_MAX + " characters.";

  public static final int DESCRIPTION_MIN = 3;
  public static final int DESCRIPTION_MAX = 60;
  public static final String INVALID_DESCRIPTION_LENGTH_MSG =
      "Description must be between " + DESCRIPTION_MIN + "and " + DESCRIPTION_MAX + " characters.";
}
