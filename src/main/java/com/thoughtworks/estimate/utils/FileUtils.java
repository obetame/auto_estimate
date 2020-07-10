package com.thoughtworks.estimate.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

  private FileUtils() {
  }

  public static String readFile(String filePath) {
    StringBuilder contentBuilder = new StringBuilder();

    try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      log.error(e.getMessage());
    }

    return contentBuilder.toString();
  }

  public static String getFileNameByPath(String filePath) {
    return new File(filePath).getName();
  }

  public static File create(String path) {
    File dir = new File(path);
    if (!dir.exists() || !dir.isDirectory()) {
      dir.mkdirs();
    }
    return dir;
  }
}
