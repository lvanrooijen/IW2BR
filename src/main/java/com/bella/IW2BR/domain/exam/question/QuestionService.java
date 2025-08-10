package com.bella.IW2BR.domain.exam.question;

import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.answer.AnswerRepository;
import com.bella.IW2BR.domain.exam.answer.dto.PostAnswer;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.question.dto.GetQuestion;
import com.bella.IW2BR.domain.exam.question.dto.PatchQuestion;
import com.bella.IW2BR.domain.exam.question.dto.PostQuestion;
import com.bella.IW2BR.domain.exam.question.dto.QuestionMapper;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.exceptions.environment.MismatchingIdException;
import com.bella.IW2BR.exceptions.exam.InvalidAnswerAmountException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    throwIfInvalidAnswerAmount(body.size().getAnswerAmount(), body.answers().size());

    Question question;
    if (body.tagId() != null) {
      Tag tag = helperMethods.getTagOrThrow(body.tagId());
      question = mapper.fromPost(body, exam, tag);
    } else {
      question = mapper.fromPost(body, exam);
    }

    questionRepository.save(question);
    List<Answer> answers = convertAndSaveAnswers(body.answers(), question);
    // add the answers manually because the answers arent available on the question yet.
    return mapper.toGet(question, answers);
  }

  public GetQuestion getById(Long environmentId, Long examId, Long id) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(examId);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    Question question = helperMethods.getQuestionOrThrow(id);

    return mapper.toGet(question);
  }

  public List<GetQuestion> getAll(Long environmentId, Long examId) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(examId);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    List<Question> questions = questionRepository.findByExamId(examId);

    return questions.stream().map(mapper::toGet).toList();
  }

  public GetQuestion update(Long environmentId, Long examId, Long id, @Valid PatchQuestion patch) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(examId);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);
    helperMethods.throwIfExamIsFinalised(exam);

    Question question = helperMethods.getQuestionOrThrow(id);
    throwIfExamIdMismatch(examId, question);

    if (patch.tagId() != null) {
      Tag tag = helperMethods.getTagOrThrow(patch.tagId());
      mapper.updateQuestionFields(question, patch, tag);
    } else {
      mapper.updateQuestionFields(question, patch);
    }

    List<Answer> answers = patchAnswers(question, patch.answers());

    questionRepository.save(question);
    return mapper.toGet(question, answers);
  }

  public List<Answer> patchAnswers(Question question, List<PostAnswer> answers) {
    throwIfInvalidAnswerAmount(question.getAnswerAmount(), answers.size());

    List<Answer> oldAnswers = answerRepository.findByQuestionId(question.getId());
    answerRepository.deleteAll(oldAnswers);

    List<Answer> newAnswers =
        answers.stream().map(answer -> mapper.fromPostAnswer(answer, question)).toList();

    answerRepository.saveAll(newAnswers);
    return newAnswers;
  }

  /**
   * Verifies if the exam in the path variable is the same as the exam connected to the question
   *
   * @param examId ID of exam provided through the path variable
   * @param question {@link Question}
   * @throws MismatchingIdException when the ID's aren't the same
   */
  private void throwIfExamIdMismatch(Long examId, Question question) {
    helperMethods.throwIfIdMismatch(
        examId,
        question.getExam().getId(),
        new MismatchingIdException(
            "Exam is not a member of the Exam specified in the path variable"));
  }

  /**
   * Verifies if the provided answers matches what is expected or throws {@link
   * InvalidAnswerAmountException}
   *
   * @param expectedAmount expected amount of {@link Answer} objects
   * @param providedAmount provided amount of {@link Answer} objects
   * @throws InvalidAnswerAmountException when the expectation is not met
   */
  private void throwIfInvalidAnswerAmount(int expectedAmount, int providedAmount) {
    if (expectedAmount != providedAmount) {
      throw new InvalidAnswerAmountException(
          String.format(
              "Question size is set to %d but %d answers we're provided",
              expectedAmount, providedAmount));
    }
  }

  /**
   * Converts the answer from dto to entity and saves it in the database
   *
   * @param answers
   * @param question
   * @return list of {@link Answer} objects that we're saved
   */
  @Transactional
  private List<Answer> convertAndSaveAnswers(List<PostAnswer> answers, Question question) {
    List<Answer> converted =
        answers.stream().map(answer -> mapper.fromPostAnswer(answer, question)).toList();
    answerRepository.saveAll(converted);
    return converted;
  }

  public void delete(Long environmentId, Long examId, Long id) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(examId);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    Question question = helperMethods.getQuestionOrThrow(id);

    if (exam.isFinalised()) {
      question.setDeleted(true);
      questionRepository.save(question);
    } else {
      questionRepository.delete(question);
    }
  }
}
