package com.bella.IW2BR.domain.exam.question.dto;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.answer.dto.PostAnswer;
import java.util.List;

/**
 * DTO representing the request body of a Question PATCH request
 *
 * @param question
 * @param answerAmount
 * @param answers List of {@link Answer}
 * @param tagId
 */
public record PostQuestion(
    String question, int answerAmount, List<PostAnswer> answers, Long tagId) {}
