package ru.kpfu.itis.water.aspects;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Aspect
@Component
public class ServiceLoggingAspect {

    @Autowired
    private Logger logger;

    @Before("within(ru.kpfu.itis.water.services..*)")
    public void log(JoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method method = methodSignature.getMethod();
        logger.info(method.getDeclaringClass().getName() + "." + method.getName() + " is called.");
    }

    @AfterThrowing(pointcut = "within(ru.kpfu.itis.water.services..*)", throwing = "ex")
    public void logException(JoinPoint jp, Exception ex) {
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        logger.error(method.getDeclaringClass().getName() + "." + method.getName() + " produced --- " + ex);
    }
}
