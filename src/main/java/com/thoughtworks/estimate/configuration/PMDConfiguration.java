package com.thoughtworks.estimate.configuration;

import com.thoughtworks.estimate.dto.pmd.PMDConfig;
import com.thoughtworks.estimate.utils.FileUtils;
import com.thoughtworks.estimate.utils.JsonUtils;
import java.nio.file.Paths;
import java.util.Map;

public class PMDConfiguration {

  private static final String PMD_CONFIG_FILENAME = "pmd.json";
  private static final PMDConfig PMD_CONFIG;

  static {
    final String filePath = Paths
        .get(CommonConfiguration.getSrcMainResourcesPath(), PMD_CONFIG_FILENAME).toString();
    PMD_CONFIG = JsonUtils.read(FileUtils.readFile(filePath), PMDConfig.class);
  }

  public static String getPmdRulesetsXmlPath() {
    return Paths
        .get(CommonConfiguration.getSrcMainResourcesPath(), PMD_CONFIG.getXmlPmdRulesetsFileName())
        .toString();
  }

  public static String getJsonReportFileName() {
    return PMD_CONFIG.getJsonReportFileName();
  }

  public static double getCodeSmellTotalScore() {
    return PMD_CONFIG.getCodeSmellTotalScore();
  }

  public static Map<String, Double> getCodeSmellPriorityScoreMap() {
    return PMD_CONFIG.getCodeSmellPriorityScoreMap();
  }

}
