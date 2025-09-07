package com.bella.IW2BR.domain.exam.attempt;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.exam.attempt.dto.GetAttempt;
import com.bella.IW2BR.domain.exam.attempt.dto.PostAttempt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ExamAttemptAPIDocs {
  @Operation(summary = "create exam attempt")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(
        responseCode = "400",
        description =
            EXAM_ATTEMPT_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_NOT_FINALIZED
                + SEPARATOR
                + INVALID_REQUEST_BODY
                + SEPARATOR
                + EXAM_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = EXAM_404 + SEPARATOR + ENVIRONMENT_404)
  })
  public ResponseEntity<GetAttempt> create(Long environmentId, Long examId);

  @Operation(summary = "submit exam", description = "Saves the exam attempt and submitted answers")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description =
            "Exam Attempt was already submitted"
                + SEPARATOR
                + EXAM_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_ATTEMPT_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_ATTEMPT_NOT_IN_EXAM
                + SEPARATOR
                + "The amount of answers provided does not match the amount of questions an exam has"),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + EXAM_404 + SEPARATOR + EXAM_ATTEMPT_404),
  })
  public ResponseEntity<GetAttempt> submit(
      Long environmentId, Long examId, Long attemptId, PostAttempt body);

  @Operation(summary = "Get Exam Attempt by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description =
            EXAM_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_ATTEMPT_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_ATTEMPT_NOT_IN_EXAM
                + SEPARATOR
                + INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + EXAM_404 + SEPARATOR + EXAM_ATTEMPT_404)
  })
  public ResponseEntity<GetAttempt> getById(Long environmentId, Long examId, Long id);

  @Operation(
      summary = "gets all the exam attempts",
      description =
          "Gets all the exam Attempts. Type can be provided to filter by COMPLETE, INCOMPLETE or any type of exam attempt")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = EXAM_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = EXAM_404)
  })
  public ResponseEntity<List<GetAttempt>> getAll(Long environmentId, Long examId, AttemptType type);

  @Operation(summary = "delete exam attempt")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(
        responseCode = "400",
        description =
            ENVIRONMENT_404
                + SEPARATOR
                + EXAM_404
                + SEPARATOR
                + EXAM_ATTEMPT_404
                + SEPARATOR
                + EXAM_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_ATTEMPT_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + EXAM_ATTEMPT_NOT_IN_EXAM),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY)
  })
  public ResponseEntity<Void> delete(Long environmentId, Long examId, Long id);
}
