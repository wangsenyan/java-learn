package com.wsy.algorithm.niuke;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class FooBar {
    private Semaphore semaphore1 = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private int n;

    public FooBar(int n) {
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphore1.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphore2.release();

        }
    }
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semaphore2.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphore1.release();
        }
    }
}
class FooBar1 {
    private BlockingQueue<Boolean> queue1 = new SynchronousQueue();
    private BlockingQueue<Boolean> queue2 = new SynchronousQueue();
    private int n;

    public FooBar1(int n) {
        this.n = n;
        queue1.offer(true);
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            queue1.poll();
            printFoo.run();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            queue2.offer(true);


        }
    }
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            queue2.poll();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            queue1.offer(true);
        }
    }
}