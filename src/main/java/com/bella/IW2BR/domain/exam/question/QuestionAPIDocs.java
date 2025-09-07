package com.bella.IW2BR.domain.exam.question;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.exam.question.dto.GetQuestion;
import com.bella.IW2BR.domain.exam.question.dto.PatchQuestion;
import com.bella.IW2BR.domain.exam.question.dto.PostQuestion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface QuestionAPIDocs {
  @Operation(summary = "Create new Question")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(
        responseCode = "400",
        description =
            EXAM_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_NOT_FINALIZED
                + SEPARATOR
                + INVALID_REQUEST_BODY
                + SEPARATOR
                + "Invalid amount of answers provided"),
    @ApiResponse(responseCode = "404", description = EXAM_404 + SEPARATOR + TAG_404)
  })
  public ResponseEntity<GetQuestion> create(Long environmentId, Long examId, PostQuestion body);

  @Operation(summary = "Get question by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = EXAM_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + EXAM_404 + SEPARATOR + QUESTION_404)
  })
  public ResponseEntity<GetQuestion> getById(Long environmentId, Long examId, Long id);

  @Operation(
      summary = "Get all questions",
      description = "Get all questions related to specified exam")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = EXAM_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + EXAM_404),
  })
  public ResponseEntity<List<GetQuestion>> getAll(Long environmentId, Long examId);

  @Operation(summary = "patch question")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description =
            EXAM_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_NOT_FINALIZED
                + SEPARATOR
                + QUESTION_NOT_IN_EXAM
                + SEPARATOR
                + "Invalid amount of answers provided"),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + EXAM_404 + SEPARATOR + QUESTION_404),
  })
  public ResponseEntity<GetQuestion> patch(
      Long environmentId, Long examId, Long id, PatchQuestion patch);

  @Operation(summary = "delete question")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(
        responseCode = "400",
        description =
            "Can not delete question, exam is finalized"
                + SEPARATOR
                + EXAM_ATTEMPT_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + EXAM_404),
    @ApiResponse(responseCode = "404", description = QUESTION_404),
  })
  public ResponseEntity<Void> delete(Long environmentId, Long examId, Long id);
}
