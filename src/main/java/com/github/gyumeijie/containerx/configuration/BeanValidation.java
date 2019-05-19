package com.github.gyumeijie.containerx.configuration;

import java.util.HashSet;
import java.util.Set;

public class BeanValidation {
  public static final Set<String> BEAN_ATTRIBUTE_SET = new HashSet<>();
  public static final Set<String> PROPERTY_ATTRIBUTE_SET = new HashSet<>();

  static {
    BEAN_ATTRIBUTE_SET.add("id");
    BEAN_ATTRIBUTE_SET.add("class");

    PROPERTY_ATTRIBUTE_SET.add("name");
    PROPERTY_ATTRIBUTE_SET.add("value");
  }
}
