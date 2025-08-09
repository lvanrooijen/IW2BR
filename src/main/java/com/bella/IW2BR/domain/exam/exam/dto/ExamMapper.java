package com.bella.IW2BR.domain.exam.exam.dto;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.exam.exam.Exam;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 * Service that handles mapping between DTOs and the entity.
 *
 * <p>Methods:
 *
 * <ul>
 *   <li>Map {@link PostExam} DTO to {@link Exam} entity
 *   <li>Map {@link Exam} entity to {@link GetExam}
 *   <li>Patch {@link Exam} with new data from {@link PatchExam}
 * </ul>
 */
@Service
public class ExamMapper {
  public GetExam toGet(Exam entity) {
    return new GetExam(
        entity.getId(),
        entity.getTitle(),
        entity.getDescription(),
        entity.getEnvironment().getId());
  }

  public Exam fromPost(PostExam dto, Environment environment) {
    return Exam.builder()
        .title(dto.title())
        .description(dto.description())
        .environment(environment)
        .createdAt(LocalDate.now())
        .build();
  }

  public Exam updateFields(Exam exam, PatchExam patch) {
    if (patch.title() != null) {
      exam.setTitle(patch.title());
    }
    if (patch.description() != null) {
      exam.setDescription(patch.description());
    }
    return exam;
  }
}
