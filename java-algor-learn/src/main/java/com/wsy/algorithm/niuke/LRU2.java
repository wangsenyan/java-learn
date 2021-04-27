package com.wsy.algorithm.niuke;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU2 {

    static class LRUCache extends LinkedHashMap<Integer,Integer> {
        private int limit;
        public LRUCache(int capacity) {
            super(capacity,0.75f,true);
            this.limit = capacity;
        }

        public int get(int key) {
            if(super.containsKey(key)){
                return super.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
           super.put(key,value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > limit;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 0); // 缓存是 {1=1}
        lruCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lruCache.get(1);    // 返回 1
        lruCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lruCache.get(2);    // 返回 -1 (未找到)
        lruCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lruCache.get(1);    // 返回 -1 (未找到)
        lruCache.get(3);    // 返回 3
        lruCache.get(4);    // 返回 4
    }
}
