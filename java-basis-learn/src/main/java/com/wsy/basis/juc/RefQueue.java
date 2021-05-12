package com.wsy.basis.juc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 通知回收-> 执行ReferenceQueue -> 回收垃圾
 */
public class RefQueue {
    private static ReferenceQueue<byte[]> rq = new ReferenceQueue<>();
    private static int _1M = 1024 *1024;

    public static void main(String[] args) {

        Object value = new Object();
        Map<Object,Object> map = new HashMap<>();
        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while ((k = (WeakReference) rq.remove()) != null) {
                    System.out.println((cnt++) + "回收了: " + k.get());
                }
            } catch (InterruptedException e) {

            }

        });
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 10000; i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<>(bytes,rq);
            map.put(weakReference,value);
        }
        System.out.println("map.size->" + map.size());
    }
}
