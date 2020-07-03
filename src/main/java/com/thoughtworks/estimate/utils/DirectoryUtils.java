package com.thoughtworks.estimate.utils;

import java.io.File;

public class DirectoryUtils {

  private DirectoryUtils() {
  }

  public static File create(String path) {
    File dir = new File(path);
    if (!dir.exists() || !dir.isDirectory()) {
      dir.mkdirs();
    }
    return dir;
  }
}
