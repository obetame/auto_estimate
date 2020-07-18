package com.thoughtworks.estimate.configuration;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PMDConfiguration {

  public static final String XML_PMD_RULESETS_FILE_NAME = "pmd-rulesets.xml";
  private static final String JSON_REPORT_FILE_NAME = "pmd_report.json";
  private static final double CODE_SMELL_TOTAL_SCORE = 20;
  private static final Map<String, Double> CODE_SMELL_PRIORITY_SCORE_MAP = new HashMap<>();

  static {
    PMDConfiguration.CODE_SMELL_PRIORITY_SCORE_MAP.put("1", 3d);
    PMDConfiguration.CODE_SMELL_PRIORITY_SCORE_MAP.put("2", 2d);
    PMDConfiguration.CODE_SMELL_PRIORITY_SCORE_MAP.put("3", 0.5d);
    PMDConfiguration.CODE_SMELL_PRIORITY_SCORE_MAP.put("4", 0.2d);
    PMDConfiguration.CODE_SMELL_PRIORITY_SCORE_MAP.put("5", 0.1d);
  }

  public static String getPmdRulesetsXmlPath() {
    return Paths.get(CommonConfiguration.getSrcMainResourcesPath(), XML_PMD_RULESETS_FILE_NAME)
        .toString();
  }

  public static String getJsonReportFileName() {
    return JSON_REPORT_FILE_NAME;
  }

  public static double getCodeSmellTotalScore() {
    return CODE_SMELL_TOTAL_SCORE;
  }

  public static Map<String, Double> getCodeSmellPriorityScoreMap() {
    return CODE_SMELL_PRIORITY_SCORE_MAP;
  }

}
