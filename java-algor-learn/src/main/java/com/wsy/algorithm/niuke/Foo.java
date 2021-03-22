package com.wsy.algorithm.niuke;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

class Foo {
    private Semaphore semaphore_2 = new Semaphore(0);
    private Semaphore semaphore_3 = new Semaphore(0);
    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        semaphore_2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaphore_2.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        semaphore_3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaphore_3.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo1 {
    private CountDownLatch c2 = new CountDownLatch(1);
    private CountDownLatch c3 = new CountDownLatch(1);
    public Foo1() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        c2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        c2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        c3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}