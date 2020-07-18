package com.thoughtworks.estimate.dto.test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCoverageConfig {
  private double testCoverageTotalScore;
  private double testCoverageBaseLine;
}
