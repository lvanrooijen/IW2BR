package com.bella.IW2BR.domain.flashcard.dto;

import com.bella.IW2BR.utils.constants.GlobalValidationConstraints;

public class FlashcardConstraints extends GlobalValidationConstraints {
  public static final int BODY_MIN = 3;

  public static final int FRONT_BODY_MAX = 30;
  public static final String INVALID_FRONT_BODY_LENGTH_MSG =
      "Flashcard front content must be between "
          + BODY_MIN
          + "and "
          + FRONT_BODY_MAX
          + " characters.";

  public static final int BACK_BODY_MAX = 30;
  public static final String INVALID_BACK_BODY_LENGTH_MSG =
      "Flashcard back content must be between "
          + BODY_MIN
          + "and "
          + BACK_BODY_MAX
          + " characters.";
}
