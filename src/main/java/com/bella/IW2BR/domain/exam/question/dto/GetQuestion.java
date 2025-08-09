package com.bella.IW2BR.domain.exam.question.dto;

import com.bella.IW2BR.domain.exam.answer.Answer;
import java.util.List;

/**
 * DTO representing how a Question is returned to the client
 *
 * @param id
 * @param question
 * @param answerAmount
 * @param answers
 * @param examId
 * @param tagId
 */
public record GetQuestion(
        Long id, String question, int answerAmount, List<Answer> answers, Long examId, Long tagId) {}
