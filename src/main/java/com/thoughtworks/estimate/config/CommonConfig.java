package com.thoughtworks.estimate.config;

public class CommonConfig {

  private static final String WORK_DIRECTORY = "auto_estimate";

  private CommonConfig() {
  }

  public static String getWorkDirectory() {
    return WORK_DIRECTORY;
  }

}
