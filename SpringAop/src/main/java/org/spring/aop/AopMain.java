package org.spring.aop;

import org.spring.service.ShapeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by igaurav on 1/22/2017.
 */
public class AopMain {
    public static  void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ShapeService shapeService = applicationContext.getBean("shapeService", ShapeService.class);
        System.out.println(shapeService.getCircle().getName());
    }
}
