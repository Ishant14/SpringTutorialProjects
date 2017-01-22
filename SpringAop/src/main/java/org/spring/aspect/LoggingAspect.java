package org.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by igaurav on 1/22/2017.
 */
@Aspect
public class LoggingAspect {

    @Before("execution(public String getName())")
    public void loggingAdvice(){
        System.out.println("Advice run. Get Method called");
    }

}
