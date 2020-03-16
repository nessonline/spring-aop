package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiUserControllerAspect extends ApplicationAspect {

    @Around("execution(* com.example.demo.rest.ApiUserController.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logMethod(String.format("ApiUserController.%s", joinPoint.getSignature().getName()), joinPoint.getArgs());
        Object result = joinPoint.proceed(joinPoint.getArgs());
        logResult(String.format("ApiUserController.%s", joinPoint.getSignature().getName()), result);
        return result;
    }
}
