package com.bella.IW2BR.domain.exam.attempt.questionanswer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
  List<QuestionAnswer> findAllByExamAttemptId(Long examAttemptId);
}
