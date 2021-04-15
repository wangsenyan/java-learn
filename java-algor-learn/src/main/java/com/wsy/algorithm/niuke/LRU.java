package com.wsy.algorithm.niuke;

import java.util.*;

/**
 * 此方法耗时太多
 */
public class LRU<K,V> {
    public class Node{
        private Node pre;
        private Node next;
        private K key;
        private V value;
        public Node(Node pre, Node next, K key, V value) {
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.value = value;

        }
    }
    private Node head;
    private Node tail;
    private int size;//链表长度
    public Node addFirst(K k, V v){
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
    public Node addLast(K k, V v){
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

    public Node remove(K k){
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
}