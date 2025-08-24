package com.bella.IW2BR.domain.exam.exam.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;

import com.bella.IW2BR.domain.exam.exam.Exam;
import jakarta.validation.constraints.AssertTrue;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of an Exam PATCH request
 *
 * @param title
 * @param description
 */
public record PatchExam(
    @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG) String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description,
    @AssertTrue(message = "finalise can only be true") Boolean finalise) {
  /**
   * Updates field of {@link Exam}
   *
   * @param exam {@link Exam}
   * @param patch {@link PatchExam}
   * @return updated {@link Exam}
   */
  public static Exam patch(Exam exam, PatchExam patch) {
    if (patch.title() != null) {
      exam.setTitle(patch.title());
    }
    if (patch.description() != null) {
      exam.setDescription(patch.description());
    }
    if (patch.finalise() != null) {
      exam.setFinalised(patch.finalise());
    }
    return exam;
  }
}
