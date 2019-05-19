package com.github.gyumeijie.containerx.container;

import com.github.gyumeijie.containerx.model.BeanDefinition;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class BeanGenerator {
  public static void initializeBean(Object bean, Map<String, String> properties) {
    Map<String, String> propSetters = new HashMap<>();

    for (Map.Entry<String, String> entry : properties.entrySet()) {
      String propName = entry.getKey();
      String propValue = entry.getValue();
      String propSetter = "";

      if (propName != null && propName.length() > 0) {
        propSetter = constructPropertySetterName(propName);
      }

      propSetters.put(propSetter, propValue);
    }

    Class<?> klass = bean.getClass();
    for (Method method : klass.getDeclaredMethods()) {
      String methodName = method.getName();

      if (methodName.startsWith("set")
          && method.getParameterTypes().length == 1
          && Modifier.isPublic(method.getModifiers())
          && propSetters.containsKey(methodName)) {
        try {
          method.invoke(bean, propSetters.get(methodName));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static Object createBean(BeanDefinition definition) {
    String className = definition.getClassName();
    Object bean = null;

    try {
      Class<?> klass = Class.forName(className, true, getClassLoader());
      bean = klass.getDeclaredConstructor().newInstance();
      initializeBean(bean, definition.getProperties());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return bean;
    }
  }

  private static ClassLoader getClassLoader() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    if (classLoader == null) {
      classLoader = BeanGenerator.class.getClassLoader();
    }
    return classLoader;
  }

  private static String constructPropertySetterName(String propName) {
    return "set" + String.valueOf(propName.charAt(0)).toUpperCase() + propName.substring(1);
  }
}
