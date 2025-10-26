package com.example.buildingdb.aop;

import com.example.buildingdb.dto.DtoInterface;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    @Pointcut("execution(* com.example.buildingdb.controller.ExceptionController.handleEntityNotFoundException(..))")
    private void exceptionHandlerPointcutEntityNotFound() {}

    @Pointcut("execution(* com.example.buildingdb.controller.ExceptionController.handleInvalidDataException(..))")
    private void exceptionHandlerPointcutInvalidDataException() {}

    @Pointcut("execution(* com.example.buildingdb.controller.ExceptionController.handleURISyntaxException(..))")
    private void exceptionHandlerPointcutURISyntaxException() {}

    @Pointcut("execution(* com.example.buildingdb.controller.*.*(Long))")
    private void controllerPointcutLong() {}

    @Pointcut("execution(* com.example.buildingdb.controller.*.*(Long, Long))")
    private void controllerPointcutLongLong() {}

    @Pointcut("execution(* com.example.buildingdb.controller.*.*(Long, com.example.buildingdb.dto..*))")
    private void controllerPointcutLongDTO() {}

    @Pointcut("execution(* com.example.buildingdb.controller.*.*(com.example.buildingdb.dto..*))")
    private void controllerPointcutDTO() {}


    @After("(controllerPointcutLong() || controllerPointcutLongDTO()) && args(id, ..)")
    public void logRequest(JoinPoint joinPoint, Long id) {
        String methodName = joinPoint.getSignature().getName();
        log.info(methodName + ": " + id);
    }

    @After("controllerPointcutDTO() && args(dto)")
    public void logPostRequest(JoinPoint joinPoint, DtoInterface dto) {
        String methodName = joinPoint.getSignature().getName();
        Long id = dto.getId();

        log.info(methodName + ": " + id);
    }

    @After("controllerPointcutLongLong() && args(buildingId, tagId)")
    public void logPutRequest(JoinPoint joinPoint, Long buildingId, String tagId) {
        String methodName = joinPoint.getSignature().getName();

        log.info(methodName + ": " + buildingId + "-" + tagId);
    }

    @After("(exceptionHandlerPointcutEntityNotFound() || exceptionHandlerPointcutInvalidDataException()) && args(e) ")
    public void logExceptionHandlingNormal(RuntimeException e) {
        log.info(e.getMessage());
    }

    @After("exceptionHandlerPointcutURISyntaxException() && args(e)")
    public void logExceptionHandlingCritical(Exception e) {
        log.error(e.getMessage());
    }
}
