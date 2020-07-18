package com.thoughtworks.estimate.configuration;

import com.thoughtworks.estimate.dto.git.GitConfig;
import com.thoughtworks.estimate.utils.FileUtils;
import com.thoughtworks.estimate.utils.JsonUtils;
import java.nio.file.Paths;

public class GitConfiguration {

  private static final String GIT_CONFIG_FILE_NAME = "git.json";
  private static final GitConfig GIT_CONFIG;

  static {
    final String filePath = Paths
        .get(CommonConfiguration.getSrcMainResourcesPath(), GIT_CONFIG_FILE_NAME).toString();
    GIT_CONFIG = JsonUtils.read(FileUtils.readFile(filePath), GitConfig.class);
  }

  private GitConfiguration() {
  }

  public static double getCommitMessageMinimumLength() {
    return GIT_CONFIG.getMessageLengthBaseline();
  }

  public static double getCommitMessageLengthDeductScore() {
    return GIT_CONFIG.getMessageLengthDeductScore();
  }

  public static double getCommitMessageTotalScore() {
    return GIT_CONFIG.getMessageTotalScore();
  }

  public static double getCommitMinimumQuantity() {
    return GIT_CONFIG.getMinimumQuantity();
  }

  public static double getCommitQuantityTotalScore() {
    return GIT_CONFIG.getQuantityTotalScore();
  }
}
