package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAspect extends ApplicationAspect {

    @Before("execution(* com.example.demo.service.UserService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logMethod(String.format("UserService.%s", joinPoint.getSignature().getName()), joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.example.demo.service.UserService.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logResult(String.format("UserService.%s", joinPoint.getSignature().getName()), result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.demo.service.UserService.*(..))", throwing = "ex")
    public void logThrowable(JoinPoint joinPoint, Throwable ex) {
        logThrowable(String.format("UserService.%s", joinPoint.getSignature().getName()), ex);
    }
}
