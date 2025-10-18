package com.faza.example.ddr.swm.aspect;

import com.faza.example.ddr.swm.model.annotation.RouteDataSource;
import com.faza.example.ddr.swm.service.DatabaseRoutingService;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RouteDataSourceAspect {

    @Autowired
    private DatabaseRoutingService databaseRoutingService;

    @Around("@annotation(routeDataSource)")
    public Object routeDataSourceAdvice(ProceedingJoinPoint proceedingJoinPoint,
        RouteDataSource routeDataSource) {
        return databaseRoutingService.execute(routeDataSource.name(),
            () -> proceedingJoinPoint(proceedingJoinPoint));
    }

    @SneakyThrows
    private Object proceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        return proceedingJoinPoint.proceed();
    }
}
