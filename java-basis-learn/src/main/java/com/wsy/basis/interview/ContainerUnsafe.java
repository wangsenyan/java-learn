package com.wsy.basis.interview;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 容器不安全类型（故障现象,导致原因,解决方案,优化建议）
 * 1. ArrayList
 *  - 故障现象: ConcurrentModificationException 并发修改异常
 *  - 原因: add()方法没加同步机制
 *  - 解决:
 *    - vector
 *    - Collections.synchronizedList 装饰模式,构造函数中加mutx,synchronized(mutex)
 *    - CopyOnWriteArrayList    ReentrantLock https://juejin.cn/post/6844903805683761165
 *      - transient 这个字段的生命周期仅存于调用者的内存中而不会写到磁盘里持久化，不会序列化传递
 *                  静态变量不能被序列化
 * 2. HashSet --> Collections.synchronizedSet() --> CopyOnWriteArraySet
 * 3. HashMap --> Collections.synchronizedMap() --> ConcurrentHashMap
 */
public class ContainerUnsafe {
    public static void main(String[] args) {
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>(); //非公平锁
        //List<String> list = new ArrayList<>();
        for(int i=1;i<=30;i++){
           new Thread(()->{
               list.add(UUID.randomUUID().toString().substring(0,8));
               System.out.println(list);
           },String.valueOf(i)).start();
        }
    }


}

