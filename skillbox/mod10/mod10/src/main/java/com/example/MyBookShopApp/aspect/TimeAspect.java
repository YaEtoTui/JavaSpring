package com.example.MyBookShopApp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class TimeAspect {

    private Integer countRequests = 0;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Pointcut(value = "@annotation(com.example.MyBookShopApp.annotation.TimeAspectAnnotation)")
    public void timePointcut() {
    }

    @Around("timePointcut()")
    public Object timer(ProceedingJoinPoint point) throws Throwable {
        String methodName = String.format("%s.%s",
                point.getStaticPart().getSignature().getDeclaringType().getName(),
                point.getSignature().getName()
        );

        long startAt = System.currentTimeMillis();

        try {
            return point.proceed(point.getArgs());
        } finally {
            long executionTime = System.currentTimeMillis() - startAt;

            logger.info( methodName + " execution took " + executionTime + " ms");
        }
    }

    @AfterReturning(pointcut = "timePointcut()")
    public void showCountRequests() {
        countRequests++;

        logger.info("Count actual requests: " + countRequests);
    }
}
