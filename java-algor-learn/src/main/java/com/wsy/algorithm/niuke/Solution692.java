package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution692 {
    class Node{
        String s;
        int num;
        int idx;
        public Node(String s,int n){
            this.s = s;
            this.num = n;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        int n  = words.length;
        //记录单词最早出现位置
        Map<String,Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = words[i];
            Node node = map.getOrDefault(s, new Node(s, 0));
            node.num++;
            map.put(s,node);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.num == o2.num ? o2.s.compareTo(o1.s) : o1.num - o2.num;
            }
        });
        for (Node v : map.values()) {
            if(pq.size() < k) pq.offer(v);
            else{
                Node p = pq.peek();
                if(p.num < v.num || (p.num == v.num && p.s.compareTo(v.s) > 0))
                {
                    pq.poll();
                    pq.offer(v);
                }
            }
        }
        while (!pq.isEmpty())
            res.add(0,pq.poll().s);
        return res;
        //最小堆,按什么顺序???

    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 3;
        List<String> strings = new Solution692().topKFrequent(words, k);
        System.out.println(strings);
    }
}
