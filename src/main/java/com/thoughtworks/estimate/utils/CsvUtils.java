package com.thoughtworks.estimate.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public class CsvUtils {

  private CsvUtils() {
  }

  @SneakyThrows
  public static <T> List<T> read(String csv, Class<T> clazz) {
    return new CsvToBeanBuilder<T>(new StringReader(csv)).withType(clazz)
        .build().stream().collect(Collectors.toList());
  }
}
