package com.wsy.spring.bean;

public class Order {
    private String name;
    public void setName(String name) {
        this.name = name;
        System.out.println("设置属性");
    }
    public Order(){
        System.out.println("构造函数");
    }
    public void initMethod(){
        System.out.println("初始化方法");
    }
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                '}';
    }
    //只有bean实实在在销毁后才会调用
    public void destroyMethod(){
        System.out.println("销毁方法");
    }
}
