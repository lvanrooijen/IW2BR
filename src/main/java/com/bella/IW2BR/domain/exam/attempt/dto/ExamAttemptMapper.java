package com.bella.IW2BR.domain.exam.attempt.dto;

import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.exam.Exam;
import org.springframework.stereotype.Service;

@Service
public class ExamAttemptMapper {
  public GetExamAttempt toGet(ExamAttempt attempt) {
    Exam exam = attempt.getExam();
    return new GetExamAttempt(
        attempt.getId(), exam.getId(), exam.getTitle(), exam.getDescription());
  }
}
