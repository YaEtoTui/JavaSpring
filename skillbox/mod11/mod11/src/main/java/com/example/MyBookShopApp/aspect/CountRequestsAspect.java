package com.example.MyBookShopApp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CountRequestsAspect {
    private Integer countRequests = 0;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Pointcut(value = "within(com.example.MyBookShopApp.controllers.AboutController)")
    public void pagesMethodsPointcut() {

    }

    @AfterReturning(pointcut = "pagesMethodsPointcut()")
    public void showCountRequests() {
        countRequests++;

        logger.info("Count actual requests: " + countRequests);
    }
}
