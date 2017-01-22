package org.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by igaurav on 1/22/2017.
 */
@Aspect
public class LogginAspectPointCutsWildCards {


    /*if we to run on any type whether public or private ,irrespective of return type,
       any type get method like getName, get getTriangle etc then user = @Before("execution(* get*())")  */

    /*if we want to run advice on any get method whether it takes argument or not then we will write as =  get*(..) */

    /* if we want to run the get method but of particular package then we will write as =
       @Before("execution(* packageName.className.get*())")   */

    @Before("allgetters()")
    public void loggingAdvice(){
        System.out.println("Advice run, get method called");
    }

    @Before("allgetters()")
    public void secondAdvice(){
        System.out.println("second Advice run, get method called");
    }

    @Pointcut("execution(* get*())")
    public void allgetters(){}

}
