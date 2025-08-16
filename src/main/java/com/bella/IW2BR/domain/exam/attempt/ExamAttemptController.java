package com.bella.IW2BR.domain.exam.attempt;

import com.bella.IW2BR.domain.exam.attempt.dto.GetExamAttempt;
import com.bella.IW2BR.domain.exam.attempt.dto.PostExamAttempt;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for handling handling operations related to Exam Attempts
 *
 * <p>The Exam Attempts endpoint contains the following path variables:
 *
 * <ul>
 *   <li>environmentId
 *   <li>examId
 * </ul>
 *
 * Example: environments/{environmentId}/exams/{examId}/attempts
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.EXAM_ATTEMPTS)
public class ExamAttemptController {
  private final ExamAttemptService attemptService;

  @PostMapping
  public ResponseEntity<GetExamAttempt> create(
      @PathVariable Long environmentId, @PathVariable Long examId) {
    GetExamAttempt attempt = attemptService.create(environmentId, examId);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(attempt.id())
            .toUri();

    return ResponseEntity.created(location).body(attempt);
  }

  @PostMapping("/{attemptId}/save")
  public ResponseEntity<GetExamAttempt> complete(
      @PathVariable Long environmentId,
      @PathVariable Long examId,
      @PathVariable Long attemptId,
      @Valid @RequestBody PostExamAttempt body) {
    // TODO maak plan

    // GetExamAttempt attempt = attemptService.complete(environmentId, examId, attemptId, body);

    // return ResponseEntity.ok(attempt);
    return null;
  }
}
