package com.thoughtworks.estimate.configuration;

import com.thoughtworks.estimate.dto.naming.NamingConfig;
import com.thoughtworks.estimate.utils.FileUtils;
import com.thoughtworks.estimate.utils.JsonUtils;
import java.nio.file.Paths;
import java.util.List;

public class NamingConfiguration {

  public static final String NAMING_CONFIG_FILENAME = "naming.json";
  private static final NamingConfig NAMING_CONFIG;

  static {
    final String filePath = Paths
        .get(CommonConfiguration.getSrcMainResourcesPath(), NAMING_CONFIG_FILENAME).toString();
    NAMING_CONFIG = JsonUtils.read(FileUtils.readFile(filePath), NamingConfig.class);
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
    return getNamingTotalScore() / getTotalNumber();
  }

  public static Double getNamingTotalScore() {
    return NAMING_CONFIG.getNamingTotalScore();
  }

  private static Integer getTotalNumber() {
    return NAMING_CONFIG.getMethodNames().size() +
        NAMING_CONFIG.getTypeNames().size() +
        NAMING_CONFIG.getVariableNames().size();
  }
}
