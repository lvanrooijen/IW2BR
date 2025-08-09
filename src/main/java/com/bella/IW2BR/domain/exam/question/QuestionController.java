package com.bella.IW2BR.domain.exam.question;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.question.dto.GetQuestion;
import com.bella.IW2BR.domain.exam.question.dto.PostQuestion;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import java.net.URI;
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
public class QuestionController {
  private final QuestionService questionService;

  @PostMapping
    public ResponseEntity<GetQuestion> create(@PathVariable Long environmentId, @PathVariable Long examId, @RequestBody PostQuestion body){
      GetQuestion question = questionService.create(environmentId, examId, body);
      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.id()).toUri();
      return ResponseEntity.created(location).body(question);
  }
}
