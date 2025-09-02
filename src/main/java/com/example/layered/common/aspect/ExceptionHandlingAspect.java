package com.example.layered.common.aspect;

import com.example.layered.common.annotation.ExceptionHandled;
import com.example.layered.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ExceptionHandlingAspect {
    
    @Around("@annotation(exceptionHandled)")
    public Object handleMethodExceptions(ProceedingJoinPoint joinPoint, ExceptionHandled exceptionHandled) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String fullMethodName = className + "." + methodName;
        
        try {
            return joinPoint.proceed();
            
        } catch (Exception ex) {
            boolean shouldHandle = Arrays.stream(exceptionHandled.exceptions())
                .anyMatch(exceptionClass -> exceptionClass.isAssignableFrom(ex.getClass()));
            
            if (shouldHandle) {
                log.error("Exception in {}: {}", fullMethodName, ex.getMessage(), ex);
                
                String customMessage = exceptionHandled.value().isEmpty() 
                    ? "An error occurred in " + fullMethodName
                    : exceptionHandled.value();
                
                log.error("error message: {}", customMessage);

                if (exceptionHandled.rethrow()) {
                    throw new RuntimeException(customMessage, ex);
                }
                return ApiResponse.error("500", ex.getMessage(), ex);

            } else {
                log.debug("Unknown Exception, rethrowing: {}", ex.getMessage());
                return ApiResponse.error("500", ex.getMessage(), ex);
            }

        }
    }
    
    @Around("@within(exceptionHandled)")
    public Object handleClassExceptions(ProceedingJoinPoint joinPoint, ExceptionHandled exceptionHandled) throws Throwable {
        return handleMethodExceptions(joinPoint, exceptionHandled);
    }

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object handleApplicationLayerExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String fullMethodName = className + "." + methodName;
        
        try {
            return joinPoint.proceed();
            
        } catch (IllegalArgumentException ex) {
            log.warn("Business logic Exception in {}: {}", fullMethodName, ex.getMessage());
            return ApiResponse.error("500", ex.getMessage(), ex);
            
        } catch (RuntimeException ex) {
            log.error("Runtime exception in {}: {}", fullMethodName, ex.getMessage(), ex);
            return ApiResponse.error("500", ex.getMessage(), ex);
            
        } catch (Exception ex) {
            log.error("Unexpected exception in {}: {}", fullMethodName, ex.getMessage(), ex);
            return ApiResponse.error("500", ex.getMessage(), ex);
        }
    }
    
//    @Around("execution(* com.example.adapter.out.persistence..*(..))")
//    public Object handlePersistenceLayerExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
//        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
//        String methodName = joinPoint.getSignature().getName();
//        String fullMethodName = className + "." + methodName;
//
//        try {
//            return joinPoint.proceed();
//
//        } catch (org.springframework.dao.DataAccessException ex) {
//            log.error("Database access error in {}: {}", fullMethodName, ex.getMessage(), ex);
//            throw new RuntimeException("Database Exception failed in " + fullMethodName, ex);
//
//        } catch (Exception ex) {
//            log.error("Persistence layer error in {}: {}", fullMethodName, ex.getMessage(), ex);
//            throw new RuntimeException("Persistence Exception in " + fullMethodName, ex);
//        }
//    }
}