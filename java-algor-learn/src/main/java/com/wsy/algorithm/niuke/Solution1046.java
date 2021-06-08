package com.wsy.algorithm.niuke;

import java.util.PriorityQueue;

public class Solution1046 {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int stone : stones) {
            pq.offer(stone);
        }
        //获取最大的两块石头
        while (pq.size()>1){
            int f = pq.poll();
            int l = pq.poll();
            if(f - l > 0) {
                pq.offer(f - l);
            }
        }
        return pq.size() > 0 ? pq.peek() : 0;
    }
}
