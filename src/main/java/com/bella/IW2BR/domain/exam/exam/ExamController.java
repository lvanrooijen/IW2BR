package com.bella.IW2BR.domain.exam.exam;

import com.bella.IW2BR.utils.constants.routes.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.EXAMS)
@RequiredArgsConstructor
public class ExamController {
  private final ExamService examService;
}
