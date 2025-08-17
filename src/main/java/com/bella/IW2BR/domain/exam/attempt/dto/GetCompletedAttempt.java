package com.bella.IW2BR.domain.exam.attempt.dto;

import com.bella.IW2BR.domain.exam.attempt.questionanswer.dto.GetQuestionAnswer;
import java.util.List;

public record GetCompletedAttempt(
    Long id,
    Long examId,
    Double score,
    String examTitle,
    String examDescription,
    List<GetQuestionAnswer> questions) {}
