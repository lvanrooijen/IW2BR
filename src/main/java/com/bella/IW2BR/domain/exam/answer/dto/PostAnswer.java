package com.bella.IW2BR.domain.exam.answer.dto;

import static com.bella.IW2BR.domain.exam.exam.util.ExamConstrains.*;

import org.hibernate.validator.constraints.Length;

public record PostAnswer(
    @Length(min = ANSWER_MIN, max = ANSWER_MAX, message = INVALID_ANSWER_MSG) String answer,
    Boolean isCorrect) {}
