package com.wsy.spring.bean;

import java.util.List;

public class Father {
    public List<String> child;
    public void setChild(List<String> child) {
        this.child = child;
        System.out.println("设置属性");
    }

    public Father() {
        System.out.println("构造函数");
    }
    public void initMethod(){
        System.out.println("初始化方法");
    }
    @Override
    public String toString() {
        return "Father{" +
                "child=" + child +
                '}';
    }
}
