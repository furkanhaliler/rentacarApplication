package com.turkcell.rentacar.aspects;

import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class OtherAspects {

  @Before("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public void beforeAspect() {

    log.info("Before Aspect started.");
  }

  @After("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public void afterAspect() {

    log.info("After Aspect started.");
  }

  @AfterReturning(
      value = "execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))",
      returning = "obj")
  public void afterReturningAspect(Object obj) {

    SuccessResult successDataResult = (SuccessResult)obj;

    log.info("Method executed and returned : " + successDataResult.getMessage());
  }

  @AfterThrowing(value = "execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))", throwing = "exception")
  public void afterThrowingAspect(Exception exception) {

    log.info("Method executed and threw exception : " + exception.getMessage());
  }
}
