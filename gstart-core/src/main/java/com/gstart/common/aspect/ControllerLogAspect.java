package com.gstart.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Around("execution(* *..controller..*.*(..))")
    public Object doControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed();
        logger.info("controller \""+joinPoint.getTarget()+"\" has visited");
        return o;
    }

   /* @Around("execution(* *..service..*.*(..))")
    public Object doServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed();
        logger.info("service \""+joinPoint.getTarget()+"\" has visited");
        return o;
    }*/
}
