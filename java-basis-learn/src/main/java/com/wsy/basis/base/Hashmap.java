package com.wsy.basis.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMap遍历
 * 1、 迭代器 安全删除数据
 *   - EntrySet
 *   - KeySet
 * 2. foreach
 *   - EntrySet
 *   - KeySet
 * 3. Lambda
 * 4. Stream API
 *   - 单线程
 *   - 多线程
 *
 * HashMap,Hashtable,ConcurrentHashMap
 * - Hashtable synchronized 并发低
 * - ConcurrentHashMap cas 和 synchronized锁住桶
 */

public class Hashmap {
    private Map<Integer,String> map;
    public Hashmap(){
        map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
    }
    public void traverse1(){
        Iterator<Map.Entry<Integer,String>> iterator
                = map.entrySet().iterator();
        while (iterator.hasNext()){ //迭代,table从小到大,链表从前到后
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
    public void traverse2(){
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext())
        {
            Integer key = iterator.next();
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }
    public void traverse3(){
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
    public void traverse4(){
        for (Integer key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }
    public void traverse5(){
        //使用traverse3
        map.forEach((key,value)->{
            System.out.println(key);
            System.out.println(value);
        });
    }
    public void traverse6(){
        map.entrySet().stream().forEach((entry)->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }
    public void traverse7(){
        map.entrySet().parallelStream().forEach((entry)->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }
    public static void main(String[] args) {
        Hashmap hashmap = new Hashmap();
        hashmap.traverse1();
    }
}
