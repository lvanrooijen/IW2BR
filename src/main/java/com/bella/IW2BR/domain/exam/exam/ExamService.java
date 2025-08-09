package com.bella.IW2BR.domain.exam.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {
  private final ExamRepository examRepository;
}
