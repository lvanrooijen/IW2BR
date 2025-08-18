package com.bella.IW2BR.domain.statistics;

import com.bella.IW2BR.utils.constants.routes.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.STATISTICS)
@RequiredArgsConstructor
public class EnvironmentStatisticsController {
  private final EnvironmentStatisticsService environmentStatisticsService;
  // TODO finish
  // get statistics by environment id

  // get all statistics by user id

  // get all statitics (if admin)
}
