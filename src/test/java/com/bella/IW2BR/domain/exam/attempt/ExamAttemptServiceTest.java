package com.bella.IW2BR.domain.exam.attempt;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.exam.attempt.dto.GetAttempt;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.QuestionAnswerRepository;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.exceptions.exam.FinalisedExamException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Exam Attempt service Tests")
@ExtendWith(MockitoExtension.class)
class ExamAttemptServiceTest {

  @Mock private ExamAttemptRepository attemptRepository;
  @Mock private EnvironmentHelperMethods helperMethods;
  @Mock private QuestionAnswerRepository questionAnswerRepository;

  @InjectMocks private ExamAttemptService attemptService;

  @Nested
  @DisplayName("Create Method Tests")
  class createTests {
    @Test
    void createShouldCreateExamAttempt() {
      Exam mockExam = getMockExam();
      mockExam.setFinalised(true);

      when(helperMethods.getExamOrThrow(VALID_ID)).thenReturn(mockExam);
      when(helperMethods.getEnvironmentOrThrow(VALID_ID)).thenReturn(getMockEnvironment());

      GetAttempt attempt = attemptService.create(VALID_ID, VALID_ID);

      assertEquals("The Initial Exam", attempt.examTitle());
      assertEquals("My First Exam!", attempt.examDescription());
    }

    @Test
    void createShouldSaveExamAttempt() {
      Exam mockExam = getMockExam();
      mockExam.setFinalised(true);

      when(helperMethods.getExamOrThrow(VALID_ID)).thenReturn(mockExam);
      when(helperMethods.getEnvironmentOrThrow(VALID_ID)).thenReturn(getMockEnvironment());

      attemptService.create(VALID_ID, VALID_ID);

      verify(attemptRepository).save(any());
    }

    @Test
    void shouldCallEnsureEnvironmentExistsAndUserIsOwnerOrAdmin() {
      Exam mockExam = getMockExam();
      mockExam.setFinalised(true);

      when(helperMethods.getExamOrThrow(VALID_ID)).thenReturn(mockExam);
      when(helperMethods.getEnvironmentOrThrow(VALID_ID)).thenReturn(getMockEnvironment());

      attemptService.create(VALID_ID, VALID_ID);

      verify(helperMethods).ensureEnvironmentExistsAndUserIsOwnerOrAdmin(any());
    }

    @Test
    void shouldCallGetExamOrThrow() {
      Exam mockExam = getMockExam();
      mockExam.setFinalised(true);

      when(helperMethods.getExamOrThrow(VALID_ID)).thenReturn(mockExam);
      when(helperMethods.getEnvironmentOrThrow(VALID_ID)).thenReturn(getMockEnvironment());

      attemptService.create(VALID_ID, VALID_ID);

      verify(helperMethods).getExamOrThrow(any());
    }

    @Test
    void shouldCallGetEnvironmentOrThrow() {
      Exam mockExam = getMockExam();
      mockExam.setFinalised(true);

      when(helperMethods.getExamOrThrow(VALID_ID)).thenReturn(mockExam);
      when(helperMethods.getEnvironmentOrThrow(VALID_ID)).thenReturn(getMockEnvironment());

      attemptService.create(VALID_ID, VALID_ID);

      verify(helperMethods).getEnvironmentOrThrow(any());
    }

    @Test
    void shouldThrowIfExamIsNotFinalised() {
      when(helperMethods.getExamOrThrow(VALID_ID)).thenReturn(getMockExam());
      when(helperMethods.getEnvironmentOrThrow(VALID_ID)).thenReturn(getMockEnvironment());

      FinalisedExamException exception =
          assertThrows(
              FinalisedExamException.class, () -> attemptService.create(VALID_ID, VALID_ID));

      assertNotNull(exception);
      assertEquals(
          "Can not create an ExamAttempt if exam is not finalised", exception.getMessage());
    }
  }
}
