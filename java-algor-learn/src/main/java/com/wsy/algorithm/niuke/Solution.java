package com.wsy.algorithm.niuke;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 线程安全呢?
 */
public class Solution {
    private HashMap<Integer,Node<Integer,Integer>> map;
    private DLinked<Integer,Integer> dLinked;
    public Solution(){
        this.dLinked = new DLinked<>();
        this.map = new HashMap<>();
    }
    class DLinked<K,V> {
        private Node<K,V> head;
        private Node<K,V> tail;
        private volatile int count = 0;
        private Node<K,V> set(Node<K,V> node){
            final Node<K,V> f = head;
            node.prev = node.next = null;
            head = node;
            if(f==null){
                tail = node;
            }else {
                node.next = f;
                f.prev = node;
            }
            count++;
            return node;
        }
        private Node<K,V> del(Node<K,V> node){
            if(node!=null){
                final Node<K,V> next = node.next;
                final Node<K,V> prev = node.prev;
                if(prev==null) {
                    head = next;
                }else{
                    prev.next = next;
                    node.next = null;
                }
                if(next==null){
                    tail = prev;
                }else {
                    next.prev = prev;
                    node.next = null;
                }
                count--;
            }
            return node;
        }
    }
    class Node<K,V>{
        private K key;
        private V value;
        Node<K,V> prev;
        Node<K,V> next;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
    public int[] LRU (int[][] operators, int k){
        int rowSize = operators.length;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=0;i<rowSize;i++){
            int opt = operators[i][0];
            if(opt == 1)
                doSet(operators[i][1],operators[i][2],k);
            else
                arrayList.add(doGet(operators[i][1]));
        }

        return  arrayList.stream().mapToInt(Integer::intValue).toArray();
    }
    private int doGet(int x){
       Node<Integer,Integer> node = map.get(x);//O(1)
       if(node != null) {
           dLinked.del(node);
           dLinked.set(node);
           return node.getValue();
       }
       return -1;
    }
    private void doSet(int x,int y,int k){
        Node<Integer,Integer> node = new Node<>(x,y);
        if(dLinked.count >=k) {
            map.remove(dLinked.tail.getKey());
            dLinked.del(dLinked.tail);
        }
        dLinked.set(node);
        map.put(x,node);
    }

    public static void main(String[] args) {
        int[][] p = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        Solution solution = new Solution();
        int[] ans = solution.LRU(p, 3);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}