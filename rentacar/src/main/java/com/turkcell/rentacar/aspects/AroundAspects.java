package com.turkcell.rentacar.aspects;

import com.turkcell.rentacar.business.requests.brand.CreateBrandRequest;
import com.turkcell.rentacar.core.utilities.results.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Aspect
@Component
@Slf4j
public class AroundAspects {

  //  @Around("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public Object aroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    log.info("Around Aspect started.");

    Object obj = proceedingJoinPoint.proceed();

    log.info("Around Aspect ended.");

    return obj;
  }

//  @Around("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public Object aroundAspect2(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    log.info(
        "Around Aspect started on method "
            + proceedingJoinPoint.getSignature()
            + " with args "
            + Arrays.toString(proceedingJoinPoint.getArgs()));

    Object obj = proceedingJoinPoint.proceed();

    log.info("Around Aspect ended on target " + proceedingJoinPoint.getTarget());

    return obj;
  }

//      @Around("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public Object aroundAspect3(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    log.info(
        "Around Aspect started on method "
            + proceedingJoinPoint.getSignature()
            + " with args "
            + Arrays.toString(proceedingJoinPoint.getArgs()));

    CreateBrandRequest createBrandRequest = new CreateBrandRequest();
    createBrandRequest.setBrandName("Ferrari");

    Object[] newArgs = {createBrandRequest};

    Object obj = proceedingJoinPoint.proceed(newArgs);

    log.info("Around Aspect ended on target " + proceedingJoinPoint.getTarget());

    return obj;
  }

      @Around("execution(* com.turkcell.rentacar.business.concretes.BrandManager.add(..))")
  public Object aroundAspect4(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    log.info(
        "Around Aspect started on method "
            + proceedingJoinPoint.getSignature()
            + " with args "
            + Arrays.toString(proceedingJoinPoint.getArgs()));

    CreateBrandRequest createBrandRequest = (CreateBrandRequest) proceedingJoinPoint.getArgs()[0];

    Object obj;

    if (createBrandRequest.getBrandName().length() > 2) {
      obj = proceedingJoinPoint.proceed();
    } else {
      obj = new Result(false, "Must be longer than 2 characters.");
    }

    log.info("Around Aspect ended on target " + proceedingJoinPoint.getTarget());

    return obj;
  }
}
