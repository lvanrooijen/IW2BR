package com.bella.IW2BR.domain.statistics;

import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentStatisticsService {
  private final EnvironmentHelperMethods helperMethods;
}
