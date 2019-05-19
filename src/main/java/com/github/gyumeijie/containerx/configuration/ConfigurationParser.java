package com.github.gyumeijie.containerx.configuration;

import com.github.gyumeijie.containerx.model.BeanDefinition;
import com.github.gyumeijie.containerx.model.PropertyDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationParser {

  public static Document parseXMLFile(String file) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    Document document = null;

    try {
      InputStream inputStream = getDocumentInputStream(file);
      InputSource inputSource = new InputSource(inputStream);
      document = factory.newDocumentBuilder().parse(inputSource);
    } catch (SAXException | IOException | ParserConfigurationException e) {
      e.printStackTrace();
    }

    return document;
  }

  private static InputStream getDocumentInputStream(String file) {
    return ConfigurationParser.class.getClassLoader().getResourceAsStream(file);
  }

  @Deprecated
  public static Map<String, BeanDefinition> getBeanDefinitions(String file) throws Exception {
    if (file == null || file.length() == 0) {
      throw new IllegalArgumentException("The name of file should not be empty");
    }

    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    Document document = ConfigurationParser.parseXMLFile(file);
    NodeList beansList = document.getElementsByTagName("bean");
    if (beansList != null) {
      for (int beanIndex = 0; beanIndex < beansList.getLength(); beanIndex++) {
        Node beanNode = beansList.item(beanIndex);
        BeanDefinition beanDefinition = parseBean(beanNode);
        beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
      }
    }

    return beanDefinitionMap;
  }

  public static Map<String, BeanDefinition> getBeanDefinitions(Document document) throws Exception {
    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    NodeList beansList = document.getElementsByTagName("bean");
    if (beansList != null) {
      for (int beanIndex = 0; beanIndex < beansList.getLength(); beanIndex++) {
        Node beanNode = beansList.item(beanIndex);
        BeanDefinition beanDefinition = parseBean(beanNode);
        beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
      }
    }

    return beanDefinitionMap;
  }

  private static BeanDefinition parseBean(Node beanNode) {
    BeanDefinition beanDefinition = new BeanDefinition();
    Map<String, String> properites = new HashMap<>();

    // Store id and classname of a bean
    NamedNodeMap attrs = beanNode.getAttributes();
    if (attrs.getLength() > 0) {
      for (int attrIndex = 0; attrIndex < attrs.getLength(); attrIndex++) {
        String attrName = attrs.item(attrIndex).getNodeName();
        String attrValue = attrs.item(attrIndex).getNodeValue();

        if (attrName.equals("id")) {
          beanDefinition.setId(attrValue);
        } else if (attrName.equals("class")) {
          beanDefinition.setClassName(attrValue);
        }
      }
    }

    // Store properties of a bean
    NodeList propsList = beanNode.getChildNodes();
    if (propsList != null) {
      for (int propIndex = 0; propIndex < propsList.getLength(); propIndex++) {
        NamedNodeMap prop = propsList.item(propIndex).getAttributes();

        if (prop != null) {
          PropertyDefinition propertyDefinition = new PropertyDefinition();
          for (int attrIndex = 0; attrIndex < prop.getLength(); attrIndex++) {
            String attrName = prop.item(attrIndex).getNodeName();
            String attrValue = prop.item(attrIndex).getNodeValue();

            if (attrName.equals("name")) {
              propertyDefinition.setName(attrValue);
            } else if (attrName.equals("value")) {
              propertyDefinition.setValue(attrValue);
            }
          }
          properites.put(propertyDefinition.getName(), propertyDefinition.getValue());
        }
      }
    }

    beanDefinition.setProperties(properites);
    return beanDefinition;
  }
}
