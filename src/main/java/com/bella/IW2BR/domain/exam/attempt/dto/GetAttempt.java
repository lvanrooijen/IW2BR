package com.bella.IW2BR.domain.exam.attempt.dto;

import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.dto.GetQuestionAnswer;
import java.util.List;

/**
 * DTO representing {@link ExamAttempt} containing only the base info
 *
 * <p>Does not contain the list of questions and answers
 *
 * @param id
 * @param examId
 * @param examTitle
 * @param examDescription
 */
public record GetAttempt(
        Long id,
        Long examId,
        Double score,
        String examTitle,
        String examDescription,
        List<GetQuestionAnswer> questions) {}
