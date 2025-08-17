package com.bella.IW2BR.domain.exam.attempt.dto;

import com.bella.IW2BR.domain.exam.attempt.questionanswer.dto.PostQuestionAnswer;
import java.util.List;

/**
 * DTO representing the request body of an ExamAttempt POST request
 *
 * <p>Use this when only the instance of the exam-attempt is created
 */
public record PostAttempt(List<PostQuestionAnswer> questions) {}
