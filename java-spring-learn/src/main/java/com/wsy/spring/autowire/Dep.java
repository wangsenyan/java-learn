package com.wsy.spring.autowire;

public class Dep {
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return "Dep{" +
                "name='" + name + '\'' +
                '}';
    }
}
