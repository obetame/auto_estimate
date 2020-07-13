package com.thoughtworks.estimate.config;

public class CommonConfig {

  public static final String PROJECT_SOURCE_PATH = "/src";
  private static final String WORK_DIRECTORY = "auto_estimate";

  private CommonConfig() {
  }

  public static String getWorkDirectory() {
    return WORK_DIRECTORY;
  }

  public static String getProjectSourcePath() {
    return PROJECT_SOURCE_PATH;
  }
}
