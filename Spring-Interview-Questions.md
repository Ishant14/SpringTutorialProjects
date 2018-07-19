## What is Spring Framework?

Spring is the most broadly used framework for the development of Java Enterprise Edition applications. 
The core features of Spring can be used in developing any Java application.We can use its extensions for building various 
web applications on top of the Java EE platform, or we may just use its dependency injection provisions in simple standalone 
applications.

## What are the benefits of using Spring?

Spring targets to make Java EE development easier. Here are the advantages of using it:

**Lightweight:** there is a slight overhead of using the framework in development

**Inversion of Control (IoC):** Spring container takes care of wiring dependencies of various objects, instead of creating or looking for dependent objects

**Aspect Oriented Programming (AOP):** Spring supports AOP to separate business logic from system services

**IoC container:** it manages Spring Bean life cycle and project specific configurations

**MVC framework:** that is used to create web applications or RESTful web services, capable of returning XML/JSON responses

**Transaction management:** reduces the amount of boiler-plate code in JDBC operations, file uploading, etc., either by using Java annotations or by Spring Bean XML configuration file

**Exception Handling:** Spring provides a convenient API for translating technology-specific exceptions into unchecked exceptions

## What Spring sub-projects do you know? Describe them briefly.

- Core – a key module that provides fundamental parts of the framework, like IoC or DI
- JDBC – this module enables a JDBC-abstraction layer that removes the need to do JDBC coding for specific vendor databases
- ORM integration – provides integration layers for popular object-relational mapping APIs, such as JPA, JDO, and Hibernate
- Web – a web-oriented integration module, providing multipart file upload, Servlet listeners, and web-oriented application context functionalities
- MVC framework – a web module implementing the Model View Controller design pattern
- AOP module – aspect-oriented programming implementation allowing the definition of clean method-interceptors and pointcuts

## What is Inversion of Control?

Inversion of Control is a principle in software engineering by which the control of objects or portions of a program is transferred to a container or framework. It’s most often used in the context of object-oriented programming.

By contrast with traditional programming, in which our custom code makes calls to a library, IoC enables a framework to take control of the flow of a program and make calls to our custom code. To enable this, frameworks use abstractions with additional behavior built in. If we want to add our own behavior, we need to extend the classes of the framework or plugin our own classes.

The advantages of this architecture are:

decoupling the execution of a task from its implementation
making it easier to switch between different implementations
greater modularity of a program
greater ease in testing a program by isolating a component or mocking its dependencies and allowing components to communicate through contracts
Inversion of Control can be achieved through various mechanisms such as: Strategy design pattern, Service Locator pattern, Factory pattern, and Dependency Injection (DI).

## What is Dependency Injection?

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

## What is Spring IOC container ?

An IoC container is a common characteristic of frameworks that implement IoC.

In the Spring framework, the IoC container is represented by the interface ApplicationContext. The Spring container is responsible for instantiating, configuring and assembling objects known as beans, as well as managing their lifecycle.

The Spring framework provides several implementations of the ```ApplicationContext``` interface — ```ClassPathXmlApplicationContext``` and ```FileSystemXmlApplicationContext``` for standalone applications, and ```WebApplicationContext``` for web applications.

In order to assemble beans, the container uses configuration metadata, which can be in the form of XML configuration or annotations.

Here’s one way to manually instantiate a container:

```java
ApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
```

Some of the useful ApplicationContext implementations that we use are;

- **AnnotationConfigApplicationContext:** For standalone java applications using annotations based configuration.
- **ClassPathXmlApplicationContext:** For standalone java applications using XML based configuration.
- **FileSystemXmlApplicationContext:** Similar to ClassPathXmlApplicationContext except that the xml configuration file can be loaded from anywhere in the file system.
- **AnnotationConfigWebApplicationContext** and **XmlWebApplicationContext** for web applications.

## What is the  difference in BeanFactory and Application Context ?

**BeanFactory**




## How dependency Injection is done in Spring ?

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

## Which is the best way of injecting beans and why

The recommended approach is to use constructor arguments for mandatory dependencies and setters for optional ones. Constructor injection allows injecting values to immutable fields and makes testing easier.

## What is a Spring Bean?

Any normal java class that is initialized by Spring IoC container is called Spring Bean. We use Spring ApplicationContext to get the Spring Bean instance.

Spring IoC container manages the life cycle of Spring Bean, bean scopes and injecting any required dependencies in the bean.

## What are different ways to configure a class as Spring Bean?

There are three different ways to configure Spring Bean.

1. **XML Configuration:** This is the most popular configuration and we can use bean element in context file to configure a Spring Bean. For example:

```
<bean name="myBean" class="com.journaldev.spring.beans.MyBean"></bean>
```
2. **Java Based Configuration:** If you are using only annotations, you can configure a Spring bean using @Bean annotation. This annotation is used with @Configuration classes to configure a spring bean. Sample configuration is:

```java
@Configuration
@ComponentScan(value="com.journaldev.spring.main")
public class MyConfiguration {

	@Bean
	public MyService getService(){
		return new MyService();
	}
}
```

To get this bean from spring context, we need to use following code snippet:

```java
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
		MyConfiguration.class);
MyService service = ctx.getBean(MyService.class);
```

3. **Annotation Based Configuration:** We can also use @Component, @Service, @Repository and @Controller annotations with classes to configure them to be as spring bean. For these, we would need to provide base package location to scan for these classes. For example:

```java
context:component-scan base-package="com.journaldev.spring" />
```

## What are different scopes of Spring Bean?

There are five scopes defined for Spring Beans.

- **singleton:** Only one instance of the bean will be created for each container. This is the default scope for the spring beans. While using this scope, make sure spring bean doesn’t have shared instance variables otherwise it might lead to data inconsistency issues because it’s not thread-safe.
- **prototype:** A new instance will be created every time the bean is requested.
request: This is same as prototype scope, however it’s meant to be used for web applications. A new instance of the bean will be created for each HTTP request.
- **session:** A new bean will be created for each HTTP session by the container.
- **global-session:** This is used to create global session beans for Portlet applications.

Spring Framework is extendable and we can create our own scopes too, however most of the times we are good with the scopes provided by the framework.

> To set spring bean scopes we can use “scope” attribute in bean element or @Scope annotation for annotation based configurations.

## What is the default bean scope in Spring framework?
 By default, a Spring Bean is initialized as a singleton.

## Are singleton beans thread-safe?

The default scope of Spring bean is singleton, so there will be only one instance per context. That means that all the having a class level variable that any thread can update will lead to inconsistent data. Hence in default mode spring beans are not thread-safe.

However we can change spring bean scope to request, prototype or session to achieve thread-safety at the cost of performance. It’s a design decision and based on the project requirements.

## How many beans will be created in following scenario  

```java
 If i create the 2 beans for a class Employee as
 <id=emp1 class="Employee" scope="singleton">
 <id=emp2 class="Employee" scope="singleton">
 then how many bean of Employee class will be created ?
  ```

This is a very classy question to test the knowledge of a person whether he actually know the what does singleton scope in spring means or not .

Here 2 different beans will be created with different id .

Now generally the question which comes to mind to beginner is that we have given scope singleton then why 2 beans will be created .

Actually Spring singleton scope is totally different from Java Singleton . Java singleton means that only one object of that class will be created per jvm or classloader. Whereas spring singleton means that once an instance is created with specific id then always same instance will be returned when we will try to do context.getBean() with same id.

**We can create N beans with with N different id in spring with all of them as singleton scope.**

## Singleton design pattern vs Singleton beans in Spring container

The Java singleton is scoped by the Java class loader, the Spring singleton is scoped by the container context.

Which basically means that, in Java, you can be sure a singleton is a truly a singleton only within the context of the class loader which loaded it. Other class loaders should be capable of creating another instance of it (provided the class loaders are not in the same class loader hierarchy), despite of all your efforts in code to try to prevent it.

In Spring, if you could load your singleton class in two different contexts and then again we can break the singleton concept.

So, in summary, Java considers something a singleton if it cannot create more than one instance of that class within a given class loader, whereas Spring would consider something a singleton if it cannot create more than one instance of a class within a given container/context.

I find "per container per bean" difficult to apprehend. I would say "one bean per bean id".Lets have an example to understand it. We have a bean class Sample. I have defined two beans from this class in bean definition, like:

```java
<bean id="id1" class="com.example.Sample" scope="singleton">
        <property name="name" value="James Bond 001"/>    
</bean>    
<bean id="id7" class="com.example.Sample" scope="singleton">
        <property name="name" value="James Bond 007"/>    
</bean>
```

So when ever I try to get the bean with id "id1",the spring container will create one bean, cache it and return same bean where ever refered with id1. If I try to get it with id7, another bean will be created from Sample class, same will be cached and returned each time you referred that with id7.

This is unlikely with Singleton pattern. In Singlton pattern one object per class loader is created always. But in spring many objects are being created for the same class. However in Spring making the scope as Singleton returning same object for the same id.

 
 ## Is it possible to create a bean if we make class constructor private ?

Yes, Spring can invoke private constructors. If it finds a constructor with the right arguments, regardless of visibility, it will use reflection to set its constructor to be accessible.

## Is it possible to inject the dependency if the setter is private ?

No it is not possible to inject the dependency if we make the setter method private.



## Why it is not possible to inject the dependency in private setter method where as it is possible to inject the dependency with private constructor ?

Spring do not need to know about your private data member, that's why private setters are not supported. Spring does want to break the actual meaning private access modifier , doing so will create a lot of design problem. Hence Spring does not allow that.

Now coming on to the private constructor , as you said it follows the singleton design patterns. To support this spring allows you to create singleton beans.

In other terms spring allow to create a bean even with private constructor , so that you could create your actual Java Singleton type Spring Singleton bean.

> Spring is completely based on reflection. It follows all the design principle and gives the power to the developer.


## What is Spring Bean life cycle?

Life of traditional java objects starts on calling new operator which instantiates the object and finalize() method is getting called when the object is eligible for garbage collection. Life cycle of Spring beans are different as compared to traditional java objects.

Spring framework provides the following ways which can be used to control the lifecycle of  bean:

1. InitializingBean and DisposableBean callback interfaces
2. Bean Name, bean factory and Application Context  Aware interfaces for specific behavior
3. custom init() and destroy() methods in bean configuration file

![alt text](https://github.com/Ishant14/SpringTutorialProjects/blob/master/images/spring-bean-lifecycle.png)


http://www.wideskills.com/spring/spring-bean-lifecycle

## What is Bean wiring and @Autowired annotation?

The process of injection spring bean dependencies while initializing it called Spring Bean Wiring.

Usually it’s best practice to do the explicit wiring of all the bean dependencies, but spring framework also supports autowiring. We can use **@Autowired** annotation with fields or methods for autowiring **byType**. For this annotation to work, we also need to enable annotation based configuration in spring bean configuration file. This can be done by context:annotation-config element.

## 
