package com.wsy.algorithm.niuke;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue<T> {
    private List<T> queue;
    private int len;
    private int count;
    private ReentrantLock rl = new ReentrantLock();
    private Condition cdt = rl.newCondition();//取的条件
    private Condition cdp = rl.newCondition();//放的条件
    MyQueue(int len){
        this.len = len;
        queue = new LinkedList<T>();
    }
    public T take()  {
        rl.lock();
        T r = null;
        try {
            while (queue.isEmpty())
                cdt.await();
            r = queue.remove(0);
            count--;
            cdp.signalAll();
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            rl.unlock();
            return r;
        }
    }
    public void push(T t){
        rl.lock();
        try {
            while (count>len) cdp.await();
            queue.add(t);
            count++;
            cdt.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rl.unlock();
        }
    }
}
