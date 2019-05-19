package com.github.gyumeijie.containerx.factory;

import com.github.gyumeijie.containerx.configuration.ConfigurationParser;
import com.github.gyumeijie.containerx.container.BeanRegistry;
import com.github.gyumeijie.containerx.model.BeanDefinition;
import org.w3c.dom.Document;

import java.util.Map;

public class DefaultBeanFactory implements BeanFactory {

  public DefaultBeanFactory(String configFile) {
    try {
      Document document = ConfigurationParser.parseXMLFile(configFile);
      Map<String, BeanDefinition> beanDefinitionMap;

      beanDefinitionMap = ConfigurationParser.getBeanDefinitions(document);
      BeanRegistry.register(beanDefinitionMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Object getBean(String beanName) {
    return BeanRegistry.getSingletonBean(beanName);
  }
}
