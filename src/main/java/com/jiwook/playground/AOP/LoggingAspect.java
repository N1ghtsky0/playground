package com.jiwook.playground.AOP;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {
    private final HttpServletRequest request;

    public LoggingAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Before("execution(* com.jiwook.playground.controller.*.*(..))")
    public void beforeController() {
        log.info("Before controller");
        log.info(request.getRequestURI());
    }

    @After("within(com.jiwook.playground.controller.*)")
    public void afterController() {
        log.info("After controller");
    }
}
