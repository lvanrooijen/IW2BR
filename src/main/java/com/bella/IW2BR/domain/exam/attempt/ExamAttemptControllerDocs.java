package com.bella.IW2BR.domain.exam.attempt;

import com.bella.IW2BR.domain.exam.attempt.dto.GetAttempt;
import com.bella.IW2BR.domain.exam.attempt.dto.PostAttempt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ExamAttemptControllerDocs {
  @Operation(summary = "create exam attempt", description = "Creates a new exam attempt instance")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(
        responseCode = "400",
        description =
            "exam that the ExamAttempt is being created for is not part of the environment specified in the path"),
    @ApiResponse(responseCode = "400", description = "Exam is not finalised"),
    @ApiResponse(responseCode = "400", description = "Exam is not part of the environment"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner or ADMIN"),
    @ApiResponse(responseCode = "404", description = "exam not found"),
    @ApiResponse(responseCode = "404", description = "environment specified in path not found")
  })
  public ResponseEntity<GetAttempt> create(Long environmentId, Long examId);

  @Operation(
      summary = "submit exam",
      description = "Submits the answers of an exam attempt and saves it")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(responseCode = "400", description = "Exam Attempt was already submitted"),
    @ApiResponse(
        responseCode = "400",
        description = "Exam is not part of environment specified in path"),
    @ApiResponse(
        responseCode = "400",
        description = "Exam Attempt is not part of environment specified in path"),
    @ApiResponse(
        responseCode = "400",
        description = "Exam Attempt is not connected to exam specified in path"),
    @ApiResponse(
        responseCode = "400",
        description =
            "The amount of answers provided does not match the amount of questions an exam has"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner or ADMIN"),
    @ApiResponse(responseCode = "404", description = "Environment not found"),
    @ApiResponse(responseCode = "404", description = "Exam not found"),
    @ApiResponse(responseCode = "404", description = "Exam Attempt not found"),
  })
  public ResponseEntity<GetAttempt> submit(
      Long environmentId, Long examId, Long attemptId, PostAttempt body);

  @Operation(summary = "Get Exam Attempt by id", description = "gets an exam attempt by id")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(
        responseCode = "400",
        description = "Exam is not part of environment specified in path"),
    @ApiResponse(
        responseCode = "400",
        description = "Exam Attempt is not part of environment specified in path"),
    @ApiResponse(
        responseCode = "400",
        description = "Exam Attempt is not connected to exam specified in path"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner or ADMIN"),
    @ApiResponse(responseCode = "404", description = "Environment not found"),
    @ApiResponse(responseCode = "404", description = "Exam not found"),
    @ApiResponse(responseCode = "404", description = "Exam Attempt not found"),
  })
  public ResponseEntity<GetAttempt> getById(Long environmentId, Long examId, Long id);

  @Operation(
      summary = "gets all the exam attempts",
      description =
          "Gets all the exam Attempts. Type can be provided to filter by COMPLETE, INCOMPLETE or any type of exam attempt")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(
        responseCode = "400",
        description = "exam is not part of environment specified in path"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not owner of exam or ADMIN"),
    @ApiResponse(responseCode = "404", description = "exam not found")
  })
  public ResponseEntity<List<GetAttempt>> getAll(Long environmentId, Long examId, AttemptType type);

  @Operation(summary = "deletes exam attempt")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(responseCode = "403", description = "authenticated user is not owner or ADMIN"),
    @ApiResponse(responseCode = "400", description = "environment does not found"),
    @ApiResponse(responseCode = "400", description = "exam not found"),
    @ApiResponse(responseCode = "400", description = "exam attempt not found"),
    @ApiResponse(responseCode = "400", description = "exam not part of specified environment"),
    @ApiResponse(
        responseCode = "400",
        description = "exam attempt not part of specified environment"),
    @ApiResponse(responseCode = "400", description = "exam attempt not connected to specified exam")
  })
  public ResponseEntity<Void> delete(Long environmentId, Long examId, Long id);
}
