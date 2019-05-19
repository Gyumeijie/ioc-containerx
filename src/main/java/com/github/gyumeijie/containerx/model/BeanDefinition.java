package com.github.gyumeijie.containerx.model;

import java.util.Map;

public class BeanDefinition {
  private String id;
  private String className;
  private Map<String, String> properties;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }
}
