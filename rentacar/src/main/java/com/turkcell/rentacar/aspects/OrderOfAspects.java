package com.turkcell.rentacar.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class OrderOfAspects {

  @Around("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public Object aroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    log.info("Around Aspect started.");

    Object obj = proceedingJoinPoint.proceed();

    log.info("Around Aspect ended.");

    return obj;
  }

  @Before("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
//  @Order(2)
  public void beforeAspect() {

    log.info("Before Aspect started.");
  }

  @After("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public void afterAspect() {

    log.info("After Aspect started.");
  }

  @AfterReturning("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public void afterReturningAspect() {

    log.info("After Returning Aspect started.");
  }

  @AfterThrowing("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public void afterThrowingAspect() {

    log.info("After Throwing Aspect started.");
  }

//  @Before("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
//  @Order(1)
//  public void beforeAspect2() {
//
//    log.info("Before Aspect 2 started.");
//  }
}
