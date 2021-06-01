package com.wsy.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class UserProxy {
    //重用切入点
    @Pointcut(value = "execution(* com.wsy.spring.aop.aspect.User.add(.. ))")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(){
        System.out.println("before");
    }
    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕之前");
        joinPoint.proceed();
        System.out.println("环绕之后");
    }
    @After("pointCut()")
    public void after(){
        System.out.println("finally");
    }
    @AfterThrowing("pointCut()")
    public void error(){
        System.out.println("error");
    }
    @AfterReturning("pointCut()")
    public void fina(){//finally
        System.out.println("return");
    }
}
