package com.wsy.basis.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class SpinLock {
    private AtomicInteger lockValue = new AtomicInteger(1);//0 占用 1 空闲

    public void lock(){
        while (!tryLock()){
            //空转
        }
    }

    private boolean tryLock() {
        return lockValue.compareAndSet(1,0);
    }
    public void unlock(){
        if(!lockValue.compareAndSet(0,1))
            throw new RuntimeException("释放锁失败");

    }
}
