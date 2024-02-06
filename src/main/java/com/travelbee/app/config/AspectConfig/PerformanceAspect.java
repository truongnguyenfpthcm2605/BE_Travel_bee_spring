package com.travelbee.app.config.AspectConfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class PerformanceAspect {

    private Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("within(com.travelbee.app.controller.*.*)")
    public void controllerMethods(){};

    @Around("controllerMethods()")
    public Object measureControllerMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.nanoTime();
        // thực thi phương thức
        Object returnValue = proceedingJoinPoint.proceed();
        long end = System.nanoTime();
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Execution of "+ methodName + " took "+ TimeUnit.NANOSECONDS.toMillis(end - start)+ "ms");
        return returnValue;
    }


}
