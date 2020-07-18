package com.thoughtworks.estimate.configuration;

import com.thoughtworks.estimate.dto.test.TestCoverageConfig;
import com.thoughtworks.estimate.utils.FileUtils;
import com.thoughtworks.estimate.utils.JsonUtils;
import java.nio.file.Paths;

public class TestCoverageConfiguration {

  private static final String TEST_COVERAGE_CONFIG_FILENAME = "test-coverage.json";
  private static final TestCoverageConfig TEST_COVERAGE_CONFIG;

  static {
    final String filePath = Paths
        .get(CommonConfiguration.getSrcMainResourcesPath(), TEST_COVERAGE_CONFIG_FILENAME)
        .toString();
    TEST_COVERAGE_CONFIG = JsonUtils.read(FileUtils.readFile(filePath), TestCoverageConfig.class);
  }

  public static double getTestCoverageTotalScore() {
    return TEST_COVERAGE_CONFIG.getTestCoverageTotalScore();
  }

  public static double getTestCoverageBaseLine() {
    return TEST_COVERAGE_CONFIG.getTestCoverageBaseLine();
  }
}
