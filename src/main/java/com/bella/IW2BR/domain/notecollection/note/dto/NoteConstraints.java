package com.bella.IW2BR.domain.notecollection.note.dto;

import com.bella.IW2BR.utils.constants.GlobalValidationConstraints;

public class NoteConstraints extends GlobalValidationConstraints {
  public static final int BODY_MIN = 3;
  public static final int BODY_MAX = 300;
  public static final String INVALID_BODY_LENGTH_MSG =
      "Title must be between " + BODY_MIN + "and " + BODY_MAX + " characters.";
}
