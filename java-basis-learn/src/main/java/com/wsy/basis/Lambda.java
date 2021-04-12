package com.wsy.basis;

public class Lambda {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("lambda");
        },"lambda").start();
    }
}
