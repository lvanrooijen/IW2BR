package com.bella.IW2BR.domain.exam.question;

import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.answer.AnswerRepository;
import com.bella.IW2BR.domain.exam.answer.dto.PostAnswer;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.question.dto.GetQuestion;
import com.bella.IW2BR.domain.exam.question.dto.PostQuestion;
import com.bella.IW2BR.domain.exam.question.dto.QuestionMapper;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.exceptions.exam.InvalidAnswerAmountException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final EnvironmentHelperMethods helperMethods;
  private final QuestionMapper mapper;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  public GetQuestion create(Long environmentId, Long examId, PostQuestion body) {
    Exam exam = helperMethods.getExamOrThrow(examId);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    throwIfInvalidAnswerAmount(body.answerAmount(), body.answers().size());

    Question question;
    if (body.tagId() != null) {
      Tag tag = helperMethods.getTagOrThrow(body.tagId());
      question = mapper.fromPost(body, exam, tag);
    } else {
      question = mapper.fromPost(body, exam);
    }

    questionRepository.save(question);
    List<Answer> answers = convertAndSaveAnswers(body.answers(), question);

    return mapper.toGet(question, answers);
  }

  @Transactional
  private List<Answer> convertAndSaveAnswers(List<PostAnswer> answers, Question question) {
    List<Answer> converted =
        answers.stream().map(answer -> mapper.fromPostAnswer(answer, question)).toList();
    answerRepository.saveAll(converted);
    return converted;
  }

  private void throwIfInvalidAnswerAmount(int answerAmount, int providedAnswers) {
    if (answerAmount != 2 && answerAmount != 4 && answerAmount != 6) {
      throw new InvalidAnswerAmountException("answerAmount must be set to 2,4 or 6");
    }

    if (answerAmount != providedAnswers) {
      throw new InvalidAnswerAmountException(
          "The amount of answers must be the same as the answerAmount");
    }
  }
}
