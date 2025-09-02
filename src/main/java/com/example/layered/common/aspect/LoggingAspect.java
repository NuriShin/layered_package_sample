package com.example.layered.common.aspect;

import com.example.layered.common.annotation.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("@annotation(loggable)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String fullMethodName = className + "." + methodName;
        
        if (loggable.logParameters()) {
            Object[] args = joinPoint.getArgs();
            log.debug("Entering method: {} with parameters: {}", fullMethodName, Arrays.toString(args));
        } else {
            log.debug("Entering method: {}", fullMethodName);
        }
        
        try {
            Object result = joinPoint.proceed();
            
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            
            if (loggable.logExecutionTime()) {
                log.debug("Method {} executed in {} ms", fullMethodName, executionTime);
            }
            
            if (loggable.logResult() && result != null) {
                log.debug("Method {} returned: {}", fullMethodName, result);
            }
            
            log.debug("Exiting method: {}", fullMethodName);
            return result;
            
        } catch (Exception ex) {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            
            log.error("Method {} failed after {} ms with exception: {}", 
                fullMethodName, executionTime, ex.getMessage());
            throw ex;
        }
    }
    
    @Around("@within(loggable)")
    public Object logClassMethods(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        return logExecutionTime(joinPoint, loggable);
    }
    
    @Pointcut("execution(* com.example.layered..*(..))")
    public void applicationLayer() {}
    
    @Around("applicationLayer()")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String fullMethodName = className + "." + methodName;
        
        log.debug("Executing service method: {}", fullMethodName);
        
        try {
            Object result = joinPoint.proceed();
            
            long executionTime = System.currentTimeMillis() - startTime;
            log.debug("Service method {} completed in {} ms", fullMethodName, executionTime);
            
            return result;
            
        } catch (Exception ex) {
            long executionTime = System.currentTimeMillis() - startTime;
            log.error("Service method {} failed after {} ms: {}", 
                fullMethodName, executionTime, ex.getMessage());
            throw ex;
        }
    }
}