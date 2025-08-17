package com.bella.IW2BR.domain.exam.attempt.questionanswer.dto;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.question.Question;
import java.util.List;

/**
 * DTO representing a Containing ID's necessary to create a QuestionAnswer
 *
 * @param id ID of {@link Question}
 * @param selectedAnswers list of ID's of {@link Answer}
 */
public record PostQuestionAnswer(Long id, List<Long> selectedAnswers) {}
