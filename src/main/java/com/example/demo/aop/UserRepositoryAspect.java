package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserRepositoryAspect extends ApplicationAspect {

    @Before("execution(* com.example.demo.repository.UserRepository.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logMethod(String.format("UserRepository.%s", joinPoint.getSignature().getName()), joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.example.demo.repository.UserRepository.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logResult(String.format("UserRepository.%s", joinPoint.getSignature().getName()), result);
    }
}
