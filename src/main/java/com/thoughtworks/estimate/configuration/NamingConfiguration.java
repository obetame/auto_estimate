package com.thoughtworks.estimate.configuration;

import com.thoughtworks.estimate.dto.naming.NamingConfig;
import com.thoughtworks.estimate.utils.FileUtils;
import com.thoughtworks.estimate.utils.JsonUtils;
import java.util.List;

public class NamingConfiguration {

  private static final NamingConfig NAMING_CONFIG;
  private static final Double NAMING_TOTAL_SCORE = 10d;

  static {
    NAMING_CONFIG = JsonUtils
        .read(FileUtils.readFile(CommonConfiguration.SRC_MAIN_RESOURCES_PATH + "naming-config.json"), NamingConfig.class);
  }

  private NamingConfiguration() {
  }

  public static List<String> getTypeNames() {
    return NAMING_CONFIG.getTypeNames();
  }

  public static List<String> getMethodNames() {
    return NAMING_CONFIG.getMethodNames();
  }

  public static List<String> getVariableNames() {
    return NAMING_CONFIG.getVariableNames();
  }

  public static Double getOneItemScore() {
    if (getTotalNumber() == 0) {
      return 0d;
    }
    return NAMING_TOTAL_SCORE / getTotalNumber();
  }

  public static Double getNamingTotalScore() {
    return NAMING_TOTAL_SCORE;
  }

  private static Integer getTotalNumber() {
    return NAMING_CONFIG.getMethodNames().size() +
        NAMING_CONFIG.getTypeNames().size() +
        NAMING_CONFIG.getVariableNames().size();
  }
}
