package com.bella.IW2BR.domain.exam.exam.dto;

import com.bella.IW2BR.domain.exam.exam.Exam;

/**
 * DTO representing how the Exam is returned to the client
 *
 * @param title
 * @param description
 */
public record GetExam(Long id, String title, String description) {

  /**
   * Maps {@link Exam} to {@link GetExam}
   *
   * @param exam {@link Exam}
   * @return {@link GetExam}
   */
  public static GetExam to(Exam exam) {
    return new GetExam(exam.getId(), exam.getTitle(), exam.getDescription());
  }
}
