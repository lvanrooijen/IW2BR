package com.bella.IW2BR.domain.exam.attempt;

import com.bella.IW2BR.domain.exam.attempt.dto.GetAttempt;
import com.bella.IW2BR.domain.exam.attempt.dto.PostAttempt;
import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
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
public class ExamAttemptController implements ExamAttemptControllerDocs {
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
  public ResponseEntity<GetAttempt> submit(
      @PathVariable Long environmentId,
      @PathVariable Long examId,
      @PathVariable Long attemptId,
      @Valid @RequestBody PostAttempt body) {
    GetAttempt attempt = attemptService.submit(environmentId, examId, attemptId, body);

    return ResponseEntity.ok(attempt);
  }

  // get by id
  @GetMapping("/{id}")
  public ResponseEntity<GetAttempt> getById(
      @PathVariable Long environmentId, @PathVariable Long examId, @PathVariable Long id) {
    GetAttempt attempt = attemptService.getById(environmentId, examId, id);
    return ResponseEntity.ok(attempt);
  }

  /**
   * Endpoint for get all {@link ExamAttempt} objects
   *
   * <p>Invalid enum is handled by {@link
   * GlobalExceptionHandler#handleMethodArgumentTypeMismatchException(Exception)}
   *
   * @param environmentId
   * @param examId
   * @param type options: COMPLETE,INCOMPLETE,ALL
   */
  @GetMapping
  public ResponseEntity<List<GetAttempt>> getAll(
      @PathVariable Long environmentId,
      @PathVariable Long examId,
      @RequestParam(required = true) AttemptType type) {
    List<GetAttempt> attempts = attemptService.getAll(environmentId, examId, type);
    return ResponseEntity.ok(attempts);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable Long environmentId, @PathVariable Long examId, @PathVariable Long id) {

    attemptService.delete(environmentId, examId, id);
    return ResponseEntity.noContent().build();
  }
}
