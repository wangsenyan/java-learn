package com.wsy.circle.sync;

public class SyncStatic {
    private static int j = 0;
    public static synchronized void incr(){
        for (int i = 0; i < 10000; i++) {
            j++;
        }
    }
    public static int getJ(){
        return j;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> SyncStatic.incr());
        Thread t2 = new Thread(() -> SyncStatic.incr());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(SyncStatic.getJ());
    }
}
