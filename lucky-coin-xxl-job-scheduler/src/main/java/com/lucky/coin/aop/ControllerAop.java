package com.lucky.coin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * 1.用于监控controller 方法调用耗时
 */
@Aspect
@Configuration
@Slf4j
public class ControllerAop {

    @Around("execution(* com.binance.pool.service.pool..*(..)) ")
    public Object aroundController(ProceedingJoinPoint p) throws Throwable {
        long start = System.currentTimeMillis();
        Object o = p.proceed();
        long end = System.currentTimeMillis();
        long cost = end - start;
        long timeout = 200;
        if (cost > timeout) {
            log.info("TimeMonitorAop aroundController " + p + " " + cost + "毫秒 ");
        }
        return o;
    }
}
