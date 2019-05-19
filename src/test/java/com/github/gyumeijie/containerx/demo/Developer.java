package com.github.gyumeijie.containerx.demo;

public class Developer {
  private String name;
  private String location;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return "Hi, my name is " + name + " and my current location is in" + location;
  }
}
