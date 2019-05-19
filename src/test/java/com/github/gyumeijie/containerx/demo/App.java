package com.github.gyumeijie.containerx.demo;

import com.github.gyumeijie.containerx.factory.BeanFactory;
import com.github.gyumeijie.containerx.factory.DefaultBeanFactory;

public class App {

  public static void main(String args[]) {
    BeanFactory beanFactory = new DefaultBeanFactory("beans.xml");

    Developer me = (Developer) beanFactory.getBean("me");
    System.out.println(me);

    Developer friendOne = (Developer) beanFactory.getBean("myFriendOne");
    System.out.println(friendOne);

    Developer friendTwo = (Developer) beanFactory.getBean("myFriendTwo");
    System.out.println(friendTwo);
  }
}
