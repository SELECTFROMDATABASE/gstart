package com.gstart.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Aspect
public class ControllerLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Around("execution(* *..controller..*.*(..))")
    public Object doServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed();
        logger.info("controller \""+joinPoint.getTarget()+"\" has visited");
        return o;
    }
}
