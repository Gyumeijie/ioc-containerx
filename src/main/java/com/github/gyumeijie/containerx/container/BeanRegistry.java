package com.github.gyumeijie.containerx.container;

import com.github.gyumeijie.containerx.model.BeanDefinition;

import java.util.Map;

public class BeanRegistry {

  public static void register(Map<String, BeanDefinition> beanDefinitionMap) {
    for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
      BeanDefinition definition = entry.getValue();
      Object bean = BeanGenerator.createBean(definition);
      BeanContainer.putIfAbeset(entry.getKey(), bean);
    }
  }

  public static Object getSingletonBean(String beanName) {
    return BeanContainer.get(beanName);
  }
}
