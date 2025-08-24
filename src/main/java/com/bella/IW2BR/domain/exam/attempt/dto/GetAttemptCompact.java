package com.bella.IW2BR.domain.exam.attempt.dto;

import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;

public record GetAttemptCompact(Long id, String title, Double score, Boolean isCompleted) {
  /**
   * Maps {@link ExamAttempt} to @{@link GetAttemptCompact}
   *
   * @param attempt {@link ExamAttempt}
   * @return {@link GetAttemptCompact}
   */
  public static GetAttemptCompact to(ExamAttempt attempt) {
    return new GetAttemptCompact(
        attempt.getId(), attempt.getExam().getTitle(), attempt.getScore(), attempt.isCompleted());
  }
}
