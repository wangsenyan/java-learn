package com.wsy.circle.sync;

public class Sync {
    private int j = 0;
    public synchronized void incr(){
        for (int i = 0; i < 10000; i++) {
            j++;
        }
    }
    public int getJ(){
        return j;
    }

    public static void main(String[] args) throws InterruptedException {
        Sync sync = new Sync();
        Thread t1 = new Thread(() -> sync.incr());
        Thread t2 = new Thread(() -> sync.incr());
        t1.start();
        t2.start();
        t1.join();
        t2.join();//只能等待非自己线程

        System.out.println(sync.getJ());
    }
}
