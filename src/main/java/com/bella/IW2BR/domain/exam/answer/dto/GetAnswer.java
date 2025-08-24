package com.bella.IW2BR.domain.exam.answer.dto;

import com.bella.IW2BR.domain.exam.answer.Answer;

/**
 * DTO to representing an {@link Answer} to return to the client
 *
 * @param answer
 * @param isCorrect
 */
public record GetAnswer(String answer, boolean isCorrect) {
  /**
   * Maps {@link Answer} to {@link GetAnswer}
   *
   * @param answer {@link Answer}
   * @return {@link GetAnswer}
   */
  public static GetAnswer to(Answer answer) {
    return new GetAnswer(answer.getAnswer(), answer.isCorrect());
  }
}
