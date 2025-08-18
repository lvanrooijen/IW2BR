package com.bella.IW2BR.domain.exam.attempt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Long> {
  List<ExamAttempt> findByIsCompleted(boolean completed);
}
