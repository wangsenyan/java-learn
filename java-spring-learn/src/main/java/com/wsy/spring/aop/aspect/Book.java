package com.wsy.spring.aop.aspect;

import lombok.Data;

@Data
public class Book {
    private String id;
    private String name;
    public void book(){
        System.out.println("book");
    }
}
