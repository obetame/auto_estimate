package com.thoughtworks.estimate.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.SneakyThrows;
import org.reflections.Reflections;

public class InterfaceUtils {

  @SneakyThrows
  public static <T> List<T> getAllImplementationInstance(Class<T> clazz) {
    List<T> instances = new ArrayList<>();
    final Set<Class<? extends T>> subTypesOf = new Reflections().getSubTypesOf(clazz);
    for (Class<? extends T> type : subTypesOf) {
      instances.add(type.newInstance());
    }
    return instances;
  }

}
