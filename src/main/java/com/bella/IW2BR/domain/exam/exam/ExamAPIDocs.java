package com.bella.IW2BR.domain.exam.exam;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.exam.exam.dto.GetExam;
import com.bella.IW2BR.domain.exam.exam.dto.PatchExam;
import com.bella.IW2BR.domain.exam.exam.dto.PostExam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ExamAPIDocs {
  @Operation(
      summary = "Creates a new Exam",
      description = "Creates a new exam, does not add question and answers yet.")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(responseCode = "400", description = INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<GetExam> create(Long environmentId, PostExam body);

  @Operation(summary = "Get exam by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = EXAM_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + EXAM_404)
  })
  public ResponseEntity<GetExam> getById(Long environmentId, Long id);

  @Operation(summary = "Get all Exams", description = "Gets all exams in the specified environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<List<GetExam>> getAll(Long environmentId);

  @Operation(summary = "patch exam", description = "patch and/or finalize exam")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description =
            EXAM_NOT_FINALIZED
                + SEPARATOR
                + EXAM_ATTEMPT_NOT_IN_ENVIRONMENT
                + SEPARATOR
                + INVALID_REQUEST_BODY
                + SEPARATOR
                + "Exam can not be finalized without providing at least 6 questions"),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = EXAM_404 + SEPARATOR + ENVIRONMENT_404),
  })
  public ResponseEntity<GetExam> patch(Long environmentId, Long id, PatchExam patch);

  @Operation(
      summary = "delete exam",
      description = "delete exam, finalized exams get soft-deleted. ")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(responseCode = "400", description = EXAM_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + EXAM_404),
  })
  public ResponseEntity<Void> delete(Long environmentId, Long id);
}
