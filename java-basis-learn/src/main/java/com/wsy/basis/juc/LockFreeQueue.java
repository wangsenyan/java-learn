package com.wsy.basis.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class LockFreeQueue {
    private AtomicReferenceArray array;
    private static final Integer EMPTY = null;
    AtomicInteger head,tail;
    public LockFreeQueue(int size){
        array = new AtomicReferenceArray(new Integer[size+1]);
        head = new AtomicInteger(0);
        tail = new AtomicInteger(0);
    }
    public boolean add(Integer element){
        int index = (tail.get() + 1) % array.length();
        if(index == head.get() % array.length())
            return false;
        while (!array.compareAndSet(index,EMPTY,element)){
            return add(element);
        }
        tail.incrementAndGet();
        return true;
    }
    public Integer poll(){
        if(head.get() == tail.get()) return null;
        int index = (head.get() +1) % array.length();
        Integer ele = (Integer) array.get(index);
        if(ele == null) return poll();
        while (!array.compareAndSet(index,ele,EMPTY))
            return poll();
        head.incrementAndGet();
        return ele;
    }
}
