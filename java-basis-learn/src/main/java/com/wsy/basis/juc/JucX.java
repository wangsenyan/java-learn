package com.wsy.basis.juc;
public class JucX {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        },"").start();
    }
}
