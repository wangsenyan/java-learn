package com.wsy.spring.aop.aspect;

import org.springframework.stereotype.Component;

@Component
public class User {
    public void add(){
        //int j = 2/0;
        System.out.println("add.....");
    }
}
