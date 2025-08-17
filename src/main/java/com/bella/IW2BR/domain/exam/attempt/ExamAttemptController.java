package com.bella.IW2BR.domain.exam.attempt;

import com.bella.IW2BR.domain.exam.attempt.dto.GetAttempt;
import com.bella.IW2BR.domain.exam.attempt.dto.GetCompletedAttempt;
import com.bella.IW2BR.domain.exam.attempt.dto.PostAttempt;
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
  public ResponseEntity<GetAttempt> create(
      @PathVariable Long environmentId, @PathVariable Long examId) {
    GetAttempt attempt = attemptService.create(environmentId, examId);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(attempt.id())
            .toUri();

    return ResponseEntity.created(location).body(attempt);
  }

  @PostMapping("/{attemptId}/submit")
  public ResponseEntity<GetCompletedAttempt> submit(
      @PathVariable Long environmentId,
      @PathVariable Long examId,
      @PathVariable Long attemptId,
      @Valid @RequestBody PostAttempt body) {
    GetCompletedAttempt attempt = attemptService.submit(environmentId, examId, attemptId, body);

    return ResponseEntity.ok(attempt);
  }
}
