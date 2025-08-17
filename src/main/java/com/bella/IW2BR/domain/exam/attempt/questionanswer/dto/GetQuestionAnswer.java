package com.bella.IW2BR.domain.exam.attempt.questionanswer.dto;

import com.bella.IW2BR.domain.exam.answer.dto.GetAnswer;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.QuestionAnswer;
import java.util.List;

/**
 * DTO representing how a {@link QuestionAnswer } is returned to the client
 *
 * @param question
 * @param answers
 */
public record GetQuestionAnswer(String question, List<GetAnswer> answers) {}
