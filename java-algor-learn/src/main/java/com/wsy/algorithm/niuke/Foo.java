package com.wsy.algorithm.niuke;

import org.springframework.context.annotation.Conditional;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    Semaphore semaphore = new Semaphore(1);

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition c1 = reentrantLock.newCondition();
    Condition c2 = reentrantLock.newCondition();
    Condition c3 = reentrantLock.newCondition();
    public Foo() {

    }
    public void first(Runnable printFirst) throws InterruptedException {
        reentrantLock.lock();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        c2.signal();
        reentrantLock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        reentrantLock.lock();
        c2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c3.signal();
        reentrantLock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {

        reentrantLock.lock();
        c3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        reentrantLock.unlock();
    }

}