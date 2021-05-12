package com.wsy.basis.juc;

import com.google.common.util.concurrent.AtomicDouble;
import com.google.common.util.concurrent.AtomicDoubleArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class _Atomic {
    //基本类型
    AtomicInteger ai;
    AtomicLong al;
    AtomicBoolean ab;
    AtomicDouble ad;

    //数组类型
    AtomicIntegerArray aia;
    AtomicLongArray ala;
    AtomicDoubleArray ada;
    AtomicReferenceArray ara;
    //引用类型
    AtomicReference af;
    AtomicStampedReference asr;//带版本号的引用,解决ABA问题
    AtomicMarkableReference amr;//标记位的引用类型

    //对象的属性修改类型
    AtomicIntegerFieldUpdater aifu;//原子更新整型字段的更新器
    AtomicLongFieldUpdater alfu;
    AtomicReferenceFieldUpdater arfu;
    public void testAI(){
        AtomicInteger count = new AtomicInteger();
        class MCallable implements Callable<Integer>{
            @Override
            public Integer call() throws Exception {
                return count.incrementAndGet();
            }
        }
        List<Future<Integer>> resultList = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            Future<Integer> result = service.submit(new MCallable());
            resultList.add(result);
        }
        for (int i = 0; i < 100; i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("Main: Task %d: %d\n",i,number);
        }
    }

    public static void main(String[] args) {
        _Atomic atomic = new _Atomic();
        atomic.testAI();
    }
}
