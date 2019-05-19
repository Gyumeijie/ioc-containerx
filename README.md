# ioc-containerx
The `ioc-containerx` is a simple container for ioc, and use `xml` to configure beans. The following picture shows how it works.

![](https://raw.githubusercontent.com/Gyumeijie/assets/master/ioc-containerx/ioc.png)

## Usuage

1. configurations file: `beans.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans>
    <bean id="me" class="com.github.gyumeijie.containerx.demo.Developer">
        <property name="name" value="Gyumeijie"/>
        <property name="location" value="Hangzhou, China"/>
    </bean>

    <bean id="myFriendOne" class="com.github.gyumeijie.containerx.demo.Developer">
        <property name="name" value="yebokang"/>
        <property name="location" value="Hangzhou, China"/>
    </bean>

    <bean id="myFriendTwo" class="com.github.gyumeijie.containerx.demo.Developer">
        <property name="name" value="yehengxuan"/>
        <property name="location" value="Hangzhou, China"/>
    </bean>
</beans>
```

2. bean class demo: `Developer.java`

```java
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
```

3. application: `App.java`

```java
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

```

## Related works
- [containerx](https://github.com/liushaoming/containerx)
