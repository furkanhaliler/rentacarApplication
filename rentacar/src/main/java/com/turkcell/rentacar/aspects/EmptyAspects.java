package com.turkcell.rentacar.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class EmptyAspects {

//    @Before("execution( * com.turkcell.rentacar.business.concretes.CarManager.checkIfCarIdExists(Integer))")
//    public void beforeAdvice13(){
//
//    }
//
//    @Before("execution(* *(..))")
//    public void beforeAdvice(){
//
//    }
//
//    @Before("execution(* *.add(..))")
////    @Before("execution(* *.*add(..))")
////    @Before("execution(* *.add*(..))")
////    @Before("execution(* *.*add*(..))")
//    public void beforeAdvice2(){
//
//    }
//
//    @Before("execution(* *.*(..))" + "&& !execution(* *.*a*(..))")
//    public void beforeAdvice3(){
//
//    }
//
//    @Before("execution(* *.*z*(..))" + "|| execution(* *.*x*(..))")
//    public void beforeAdvice4(){
//
//    }
//
//    @Before("execution(* com.turkcell.rentacar.business.concretes.CarManager.*(..))")
//    public void beforeAdvice5(){
//
//    }
//
//    @Before("execution(public * com.turkcell.rentacar.business.concretes.CarManager.g*(..))")
//    public void beforeAdvice6(){
//
//    }
//
//    @Before("execution(* com.turkcell.rentacar.business.concretes.*.*(..))")
//    public void beforeAdvice7(){
//
//    }
//
//    @Before("execution(* com.turkcell.rentacar.business..*.*(..))")
//    public void beforeAdvice8(){
//
//    }
//
//    @Before("execution(* *.*(..) throws com.turkcell.rentacar.core.exceptions.BusinessException)")
//    public void beforeAdvice9(){
//
//    }
//
//    @Before("execution(* *.*(int))")
////    @Before("execution(* *.*(int, String))")
////    @Before("execution(* *.*(int,*))")
////    @Before("execution(* *.*(int,..))")
//    public void beforeAdvice10(){
//
//    }
//
//    @Before("within(com.turkcell.rentacar.business..*)")
//    public void beforeAdvice11(){
//
//    }

    @Before("@annotation(ToLog)")
    public void beforeAdvice12(){
    System.out.println("deneme");
    }
}
