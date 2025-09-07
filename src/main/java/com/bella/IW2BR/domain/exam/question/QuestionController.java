package com.bella.IW2BR.domain.exam.question;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.question.dto.GetQuestion;
import com.bella.IW2BR.domain.exam.question.dto.PatchQuestion;
import com.bella.IW2BR.domain.exam.question.dto.PostQuestion;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for handling {@link Question} and {@link Answer} related operations.
 *
 * <p>The Question endpoint contains an environmentId and examId path variable:
 * /environments/{environmentId}/exams/{examId}/questions
 */
@RestController
@RequestMapping(Endpoints.QUESTIONS)
@RequiredArgsConstructor
public class QuestionController implements QuestionAPIDocs {
  private final QuestionService questionService;

  @PostMapping
  public ResponseEntity<GetQuestion> create(
      @PathVariable Long environmentId,
      @PathVariable Long examId,
      @Valid @RequestBody PostQuestion body) {
    GetQuestion question = questionService.create(environmentId, examId, body);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(question.id())
            .toUri();
    return ResponseEntity.created(location).body(question);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetQuestion> getById(
      @PathVariable Long environmentId, @PathVariable Long examId, @PathVariable Long id) {
    GetQuestion question = questionService.getById(environmentId, examId, id);

    return ResponseEntity.ok(question);
  }

  @GetMapping
  public ResponseEntity<List<GetQuestion>> getAll(
      @PathVariable Long environmentId, @PathVariable Long examId) {
    List<GetQuestion> questions = questionService.getAll(environmentId, examId);

    return ResponseEntity.ok(questions);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetQuestion> patch(
      @PathVariable Long environmentId,
      @PathVariable Long examId,
      @PathVariable Long id,
      @Valid @RequestBody PatchQuestion patch) {
    GetQuestion question = questionService.update(environmentId, examId, id, patch);
    return ResponseEntity.ok(question);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable Long environmentId, @PathVariable Long examId, @PathVariable Long id) {
    questionService.delete(environmentId, examId, id);
    return ResponseEntity.noContent().build();
  }
}
