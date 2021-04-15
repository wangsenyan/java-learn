package com.wsy.algorithm.niuke;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Solution101 {
    public int nthUglyNumber1(int n) {
        //记录丑数
        Set<Long> set = new HashSet<>();
        int[] f = new int[]{2,3,5};
        set.add(1L);
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long min = pq.poll();
            ugly = (int) min;
            for (int j = 0; j < 3; j++) {
                long next = min * f[j];
                if(set.add(next)){
                    pq.offer(next);
                }
            }
        }
        return ugly;

    }
    public int nthUglyNumber(int n) {
        //记录丑数
        int[] ugly = new int[n+1];
        ugly[1] = 1;
        int p2 = 1,p3 = 1,p5 = 1;
        for (int i = 2; i <= n; i++) {
            int n2 = ugly[p2] * 2;
            int n3 = ugly[p3] * 3;
            int n5 = ugly[p5] * 5;
            ugly[i] = Math.min(n2,Math.min(n3,n5));
            if(ugly[i]==n2)
                p2++;
            if(ugly[i]==n3)
                p3++;
            if(ugly[i]==n5)
                p5++;
        }
        return ugly[n];
    }


    public static void main(String[] args) {
        int i = new Solution101().nthUglyNumber(10);
    }
}