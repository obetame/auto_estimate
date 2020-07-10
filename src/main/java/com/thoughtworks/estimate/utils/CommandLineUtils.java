package com.thoughtworks.estimate.utils;

import java.io.File;
import lombok.SneakyThrows;

public class CommandLineUtils {

  private CommandLineUtils() {
  }

  @SneakyThrows
  public static int runCommand(String command, File path) {
    return Runtime.getRuntime().exec(command, new String[]{}, path).waitFor();
  }
}
