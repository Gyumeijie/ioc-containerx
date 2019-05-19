package com.github.gyumeijie.containerx.container;

import java.util.concurrent.ConcurrentHashMap;

public class BeanContainer {

  private static final ConcurrentHashMap<String, Object> singletonBeans = new ConcurrentHashMap<>();

  public static Object get(String key) {
    return singletonBeans.get(key);
  }

  public static Object putIfAbeset(String key, Object value) {
    return singletonBeans.putIfAbsent(key, value);
  }

  public static Object put(String key, Object value) {
    return singletonBeans.put(key, value);
  }
}
