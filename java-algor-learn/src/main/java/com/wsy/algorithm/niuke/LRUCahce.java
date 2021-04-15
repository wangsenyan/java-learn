package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    public class Node{
        private Node pre;
        private Node next;
        private Integer key;
        private Integer value;
        public Node(Node pre, Node next,Integer key, Integer value) {
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.value = value;

        }
    }
    private Node head;
    private Node tail;
    private int size;//链表长度
    public Node addFirst(Integer k,Integer v){
        Node node = new Node(null, head, k,v);
        if(head==null){//空链
            head = node;
            tail = node;
        }else{
            head.pre = node;
            head = node;
        }
        size++;
        return node;
    }
    public Node addLast(Integer k,Integer v){
        Node node = new Node(tail, null, k,v);
        if(tail == null){
            tail = node;
            head = node;
        }else{
            tail.next = node;
            tail = node;
        }
        size++;
        return node;
    }

    public Node remove(Integer k){
        if(size<=0) return null;
        Node cur = head;
        while (cur!=null){
            if(cur.key.equals(k)){
                unlink(cur);
                size--;
                return cur;
            }
        }
        return null;
    }

    public void unlink(Node node){//删除节点
        if(node==null) return;
        Node pre = node.pre;
        Node next = node.next;
        if(pre!=null){
            pre.next = next;
            node.next = null;
        }else{
            head = next;
        }
        if(next!=null){
            next.pre = pre;
            node.pre = null;
        }else{
            tail = pre;
        }
        size--;
    }
    public int size(){
        return size;
    }
    private Map<Integer,Node> map;
    private Integer capacity;
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<Integer,Node>();
    }
    public int get(int key) {
       if(map.containsKey(key)){
           Node node = map.remove(key);
           unlink(node);//删除此节点
           Node newNode = addFirst(node.key,node.value);
           map.put(key,newNode);
           return node.value;
       }
       return -1;
    }

    public void put(int key, int value) {
        //如果包含此键,先删掉
        if(map.containsKey(key)){
            Node k = map.remove(key);
            unlink(k);
        }
        //如果容量已满,删掉最后
        if(size>=capacity){
            Node t = tail;
            unlink(t);
            map.remove(t.key);
        }
        Node node = addFirst(key,value);
        map.put(key,node);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 0); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}
