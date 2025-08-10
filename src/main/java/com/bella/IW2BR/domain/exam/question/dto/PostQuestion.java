package com.bella.IW2BR.domain.exam.question.dto;

import static com.bella.IW2BR.domain.exam.exam.util.ExamConstrains.*;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.answer.dto.PostAnswer;
import com.bella.IW2BR.domain.exam.exam.util.QuestionSize;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Question PATCH request
 *
 * @param question
 * @param questionSize {@link QuestionSize}
 * @param answers List of {@link Answer}
 * @param tagId
 */
public record PostQuestion(
    @Length(min = QUESTION_MIN, max = QUESTION_MAX, message = INVALID_QUESTION_MSG) String question,
    QuestionSize size,
    List<PostAnswer> answers,
    @Positive Long tagId) {}
