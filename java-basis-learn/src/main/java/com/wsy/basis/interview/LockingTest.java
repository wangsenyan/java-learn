package com.wsy.basis.interview;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁,非公平锁,可重入锁,递归锁,自旋锁,手写自旋锁
 * 一。公平锁
 *   - FIFO
 *   - new ReentrantLock(true)
 * 二。非公平锁
 *   - 可抢占
 *   - new ReentrantLock() Synchronized也是非公平锁
 *   - 吞吐量比公平锁较大
 *
 * 三。可重入锁/递归锁
 *   - 同一线程外层函数获得锁后,内层递归函数任然能获得改锁的代码(自动获得锁)
 *     线程可进入任何一个它已经拥有的锁所在的代码块
 *   - new ReentrantLock() synchronized
 *   - 避免死锁
 *
 * 四。自旋锁
 */
public class LockingTest {
    public static void main(String[] args) {
        //ReentrantLock lock = new ReentrantLock(true);
        //Phone phone = new Phone();
        MyPhone myPhone = new MyPhone();
        myPhone.sendMsg();
//        new Thread(()->{
//            try {
//                phone.sendSMS();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t1").start();
//
//        new Thread(()->{
//            try {
//                phone.sendSMS();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t2").start();
    }
}
class Phone{
    public synchronized void sendSMS()throws Exception{
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
        sendMail();
    }
    public synchronized void sendMail()throws Exception{
        System.out.println(Thread.currentThread().getId() + "\t invoked sendMail()");
    }
}
@Getter
@Setter
class MyPhone{
    ReentrantLock lock = new ReentrantLock();
    public void sendMsg(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
            sendMail();
        }finally {
            lock.unlock();
        }
    }
    public void sendMail(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getId() + "\t invoked sendMail()");
        }finally {
            lock.unlock();
        }
    }
}