package com.bella.IW2BR.domain.exam.exam;

import com.bella.IW2BR.domain.exam.exam.dto.GetExam;
import com.bella.IW2BR.domain.exam.exam.dto.PatchExam;
import com.bella.IW2BR.domain.exam.exam.dto.PostExam;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for handling Exam related operations.
 *
 * <p>The EXAMS endpoint contains an environmentId path variable:
 * /environments/{environmentId}/exams
 */
@RestController
@RequestMapping(Endpoints.EXAMS)
@RequiredArgsConstructor
public class ExamController {
  private final ExamService examService;

  @PostMapping
  public ResponseEntity<GetExam> create(
      @PathVariable Long environmentId, @Valid @RequestBody PostExam body) {
    GetExam exam = examService.create(environmentId, body);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(exam.id())
            .toUri();
    return ResponseEntity.created(location).body(exam);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetExam> getById(@PathVariable Long environmentId, @PathVariable Long id) {
    GetExam exam = examService.getById(environmentId, id);
    return ResponseEntity.ok(exam);
  }

  @GetMapping
  public ResponseEntity<List<GetExam>> getAll(@PathVariable Long environmentId) {
    List<GetExam> exams = examService.getAll(environmentId);
    return ResponseEntity.ok(exams);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetExam> patch(
      @PathVariable Long environmentId,
      @PathVariable Long id,
      @Valid @RequestBody PatchExam patch) {
    GetExam exam = examService.update(environmentId, id, patch);
    return ResponseEntity.ok(exam);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long environmentId, @PathVariable Long id) {
    examService.delete(environmentId, id);
    return ResponseEntity.noContent().build();
  }
}
