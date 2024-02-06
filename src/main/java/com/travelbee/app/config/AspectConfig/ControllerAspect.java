package com.travelbee.app.config.AspectConfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
@EnableAspectJAutoProxy
@Component
public class ControllerAspect {
    private Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods(){}

    @Around(" controllerMethods() && execution(* com.travelbee.app.controller.*.*.*(..))")
    public Object loggerController(ProceedingJoinPoint joinPoint) throws Throwable{
        // lấy name method
        String methodName = joinPoint.getSignature().getName();
        // địa chỉ ip request gửi đến
        String remoteAddress = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteUser();
        logger.info("User activity started : "+ methodName + ", IP Address : "+ remoteAddress);
        // thực hiện method gốc
        Object result = joinPoint.proceed();
        // ghi log sau khi thực hiện
        logger.info("User activity finished : "+ methodName);
        return  result;
    }

    @Before(" controllerMethods() && execution(* com.travelbee.app.controller.*.*.*(..))")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        logger.info(" before called method : " + method);
    }

    @After("controllerMethods() &&execution(* com.travelbee.app.controller.*.*.*(..))")
    public void after(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        logger.info(" after called method : " + method);
    }

    @AfterReturning("controllerMethods() &&execution(* com.travelbee.app.controller.*.*.*(..))")
    public void AfterReturning(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        logger.info(" AfterReturning called method  :" + method);
    }

    @AfterThrowing("controllerMethods() &&execution(* com.travelbee.app.controller.*.*.*(..))")
    public void AfterThrowing(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        logger.info(" AfterThrowing called method :" + method);
    }
}
