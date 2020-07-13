package com.thoughtworks.estimate.config;

import java.util.HashMap;
import java.util.Map;

public class PMDConfig {

  public static final String PMD_RULESETS_XML_PATH = "src/main/resources/pmd-rulesets.xml";
  private static final String JSON_REPORT_FILE_NAME = "pmd_report.json";
  private static final double CODE_SMELL_TOTAL_SCORE = 20;
  private static final Map<String, Double> CODE_SMELL_PRIORITY_SCORE_MAP = new HashMap<>();

  static {
    PMDConfig.CODE_SMELL_PRIORITY_SCORE_MAP.put("1", 3d);
    PMDConfig.CODE_SMELL_PRIORITY_SCORE_MAP.put("2", 2d);
    PMDConfig.CODE_SMELL_PRIORITY_SCORE_MAP.put("3", 0.5d);
    PMDConfig.CODE_SMELL_PRIORITY_SCORE_MAP.put("4", 0.2d);
    PMDConfig.CODE_SMELL_PRIORITY_SCORE_MAP.put("5", 0.1d);
  }

  public static String getPmdRulesetsXmlPath() {
    return PMD_RULESETS_XML_PATH;
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
