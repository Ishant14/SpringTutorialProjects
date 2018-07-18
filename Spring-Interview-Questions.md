# What is Spring Framework?

Spring is the most broadly used framework for the development of Java Enterprise Edition applications. 
The core features of Spring can be used in developing any Java application.We can use its extensions for building various 
web applications on top of the Java EE platform, or we may just use its dependency injection provisions in simple standalone 
applications.

# What are the benefits of using Spring?

Spring targets to make Java EE development easier. Here are the advantages of using it:

**Lightweight:** there is a slight overhead of using the framework in development

**Inversion of Control (IoC):** Spring container takes care of wiring dependencies of various objects, instead of creating or looking for dependent objects

**Aspect Oriented Programming (AOP):** Spring supports AOP to separate business logic from system services

**IoC container:** it manages Spring Bean life cycle and project specific configurations

**MVC framework:** that is used to create web applications or RESTful web services, capable of returning XML/JSON responses

**Transaction management:** reduces the amount of boiler-plate code in JDBC operations, file uploading, etc., either by using Java annotations or by Spring Bean XML configuration file

**Exception Handling:** Spring provides a convenient API for translating technology-specific exceptions into unchecked exceptions

# What Spring sub-projects do you know? Describe them briefly.

- Core – a key module that provides fundamental parts of the framework, like IoC or DI
- JDBC – this module enables a JDBC-abstraction layer that removes the need to do JDBC coding for specific vendor databases
- ORM integration – provides integration layers for popular object-relational mapping APIs, such as JPA, JDO, and Hibernate
- Web – a web-oriented integration module, providing multipart file upload, Servlet listeners, and web-oriented application context functionalities
- MVC framework – a web module implementing the Model View Controller design pattern
- AOP module – aspect-oriented programming implementation allowing the definition of clean method-interceptors and pointcuts

# What is Inversion of Control?

Inversion of Control is a principle in software engineering by which the control of objects or portions of a program is transferred to a container or framework. It’s most often used in the context of object-oriented programming.

By contrast with traditional programming, in which our custom code makes calls to a library, IoC enables a framework to take control of the flow of a program and make calls to our custom code. To enable this, frameworks use abstractions with additional behavior built in. If we want to add our own behavior, we need to extend the classes of the framework or plugin our own classes.

The advantages of this architecture are:

decoupling the execution of a task from its implementation
making it easier to switch between different implementations
greater modularity of a program
greater ease in testing a program by isolating a component or mocking its dependencies and allowing components to communicate through contracts
Inversion of Control can be achieved through various mechanisms such as: Strategy design pattern, Service Locator pattern, Factory pattern, and Dependency Injection (DI).

# What is Dependency Injection?

Dependency injection is a pattern through which to implement IoC, where the control being inverted is the setting of object’s dependencies.

The act of connecting objects with other objects, or “injecting” objects into other objects, is done by an assembler rather than by the objects themselves.

Here’s how you would create an object dependency in traditional programming:

```java
public class Store {
    private Item item;
  
    public Store() {
        item = new ItemImpl1();    
    }
}
```

In the example above, we need to instantiate an implementation of the Item interface within the Store class itself.

By using DI, we can rewrite the example without specifying the implementation of Item that we want:

```java
public class Store {
    private Item item;
    public Store(Item item) {
        this.item = item;
    }
}

```

# What is Spring IOC container ?

An IoC container is a common characteristic of frameworks that implement IoC.

In the Spring framework, the IoC container is represented by the interface ApplicationContext. The Spring container is responsible for instantiating, configuring and assembling objects known as beans, as well as managing their lifecycle.

The Spring framework provides several implementations of the ```ApplicationContext``` interface — ```ClassPathXmlApplicationContext``` and ```FileSystemXmlApplicationContext``` for standalone applications, and ```WebApplicationContext``` for web applications.

In order to assemble beans, the container uses configuration metadata, which can be in the form of XML configuration or annotations.

Here’s one way to manually instantiate a container:

```java
ApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
```

# How dependency Injection is done in Spring ?

Dependency Injection in Spring can be done through :
1. constructors 
2. setters  
3. fields.

 **Constructor-Based Dependency Injection**
 
 Java configuration file looks pretty much like a plain-old java object with some additional annotations:
 
 ```java
 @Configuration
@ComponentScan("com.baeldung.spring")
public class Config {
 
    @Bean
    public Engine engine() {
        return new Engine("v8", 5);
    }
 
    @Bean
    public Transmission transmission() {
        return new Transmission("sliding");
    }
}
 ```
 
 Next, we define a Car class:
 
 ```java
 @Component
public class Car {
 
    @Autowired
    public Car(Engine engine, Transmission transmission) {
        this.engine = engine;
        this.transmission = transmission;
    }
}

 ```
Spring will encounter our ```Car``` class while doing a package scan and will initialize its instance by calling the ```@Autowired``` annotated constructor.

Instances of Engine and Transmission will be obtained by calling ```@Bean``` annotated methods of the Config class. Finally, we need to bootstrap an ApplicationContext using our POJO configuration:

```java
ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
Car car = context.getBean(Car.class);
```

XML Based Configuration :- 

Another way to configure Spring runtime with constructor-based dependency injection is to use an xml configuration file:

```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
 
    <bean id="toyota" class="com.baeldung.spring.domain.Car">
        <constructor-arg index="0" ref="engine"/>
        <constructor-arg index="1" ref="transmission"/>
    </bean>
 
    <bean id="engine" class="com.baeldung.spring.domain.Engine">
        <constructor-arg index="0" value="v4"/>
        <constructor-arg index="1" value="2"/>
    </bean>
 
    <bean id="transmission" class="com.baeldung.spring.domain.Transmission">
        <constructor-arg value="sliding"/>
    </bean>
 
</beans>

```

**Setter-Based Dependency Injection**

For setter-based DI, the container will call setter methods of our class, after invoking a no-argument constructor or no-argument static factory method to instantiate the bean. Let’s create this configuration using annotations:

```java
@Bean
public Store store() {
    Store store = new Store();
    store.setItem(item1());
    return store;
}
```
We can also use XML for the same configuration of beans:

```
<bean id="store" class="org.baeldung.store.Store">
    <property name="item" ref="item1" />
</bean>
```

**Field-Based Dependency Injection**

In case of Field-Based DI, we can inject the dependencies by marking them with an @Autowired annotation:

```java
public class Store {
    @Autowired
    private Item item; 
}
```

While constructing the Store object, if there’s no constructor or setter method to inject the Item bean, the container will use reflection to inject Item into Store.


**Why Field-Based Dependency Injection should be avoied ?**

This approach might look simpler and cleaner but is not recommended to use because it has a few drawbacks such as:

- This method uses reflection to inject the dependencies, which is costlier than constructor-based or setter-based injection
- It’s really easy to keep adding multiple dependencies using this approach. If you were using constructor injection having   arguments would have made us think that the class does more than one thing which can violate the Single Responsibility    Principle.




