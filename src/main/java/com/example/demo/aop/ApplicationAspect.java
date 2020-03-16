package com.example.demo.aop;

import com.example.demo.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public abstract class ApplicationAspect {

    protected void logMethod(String method, Object[] args) {
        log.info(String.format(
                "%s call: %s",
                method,
                Stream.of(args).map(Object::toString).collect(Collectors.joining(", "))
        ));
    }

    protected void logResult(String method, Object result) {
        log.info(String.format(
                "%s return: %s",
                method,
                ObjectUtils.toJson(result)
        ));
    }

    protected void logThrowable(String method, Throwable ex) {
        log.info(String.format(
                "%s ex: %s",
                method,
                ex.getMessage()
        ));
    }
}
