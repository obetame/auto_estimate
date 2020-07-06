package com.thoughtworks.estimate.utils;

import com.google.gson.Gson;
import java.lang.reflect.Type;

public class JsonUtils {

  private JsonUtils() {
  }

  public static <T> T read(String json, Type type) {
    Gson gson = new Gson();
    return gson.fromJson(json, type);
  }

  public static String toJson(Object object) {
    Gson gson = new Gson();
    return gson.toJson(object);
  }
}
