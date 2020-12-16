package com.wsy.basis;


import javax.annotation.Resource;

@Resource
public class Pet {
    private String name;
    Pet(String name){
        this.name = name;
    }
}
