package com.wsy.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Aspect
@Component
@Order(1)
public class PersonProxy {
    @Pointcut(value = "execution(* com.wsy.spring.aop.aspect.User.add(.. ))")
    public void pointCut(){
    }
    @Before("pointCut()")
    public void before(){
        System.out.println("before.......");
    }
}
