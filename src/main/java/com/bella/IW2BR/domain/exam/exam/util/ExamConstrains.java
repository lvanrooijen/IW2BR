package com.bella.IW2BR.domain.exam.exam.util;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.question.Question;
import com.bella.IW2BR.utils.constants.GlobalValidationConstraints;

/**
 * Contains constants for validation constraints related to the Exam DTO's.
 *
 * <p>This includes constraints for {@link Answer} and {@link Question}
 *
 * <p>Extends the {@link GlobalValidationConstraints}
 */
public class ExamConstrains extends GlobalValidationConstraints {
  public static final int ANSWER_MIN = 1;
  public static final int ANSWER_MAX = 120;

  public static final int QUESTION_MIN = 6;
  public static final int QUESTION_MAX = 300;

  public static final String INVALID_ANSWER_MSG =
      "Answer must be between " + ANSWER_MIN + " and " + ANSWER_MIN + " characters";

  public static final String INVALID_QUESTION_MSG =
      "Question must be between " + QUESTION_MIN + " and " + QUESTION_MAX + " characters";
}
