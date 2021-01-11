package com.wsy.basis.juc;

import java.util.concurrent.ConcurrentHashMap;

public class JucX {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        },"").start();
    }
}
