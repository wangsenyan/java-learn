package com.wsy.algorithm.niuke;

import java.util.concurrent.*;

public class FooBar {
    BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();
    BlockingQueue<Integer> blockingQueue1 = new SynchronousQueue<>();
    private int n;

    public FooBar(int n) {
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            blockingQueue.take();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            blockingQueue.put(i);

        }
    }
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            blockingQueue.take();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            blockingQueue1.put(i);

        }
    }
}
