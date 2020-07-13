package com.thoughtworks.estimate.configuration;

public class TestCoverageConfiguration {

  private static final double TEST_COVERAGE_TOTAL_SCORE = 10;
  private static final double TEST_COVERAGE_BASE_LINE = 0.9;

  public static double getTestCoverageTotalScore() {
    return TEST_COVERAGE_TOTAL_SCORE;
  }

  public static double getTestCoverageBaseLine() {
    return TEST_COVERAGE_BASE_LINE;
  }
}
