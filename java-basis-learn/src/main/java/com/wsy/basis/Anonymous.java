package com.wsy.basis;

public class Anonymous {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous");
            }
        },"Anonymous").start();
    }
}
