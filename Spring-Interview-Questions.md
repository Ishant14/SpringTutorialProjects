# Q1. What is Spring Framework?

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

The Spring framework provides several implementations of the ```java ApplicationContext``` interface — ```ClassPathXmlApplicationContext``` and ```FileSystemXmlApplicationContext``` for standalone applications, and ```WebApplicationContext``` for web applications.

In order to assemble beans, the container uses configuration metadata, which can be in the form of XML configuration or annotations.

Here’s one way to manually instantiate a container:

```java
ApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
```




