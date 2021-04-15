package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution69 {
    public int[] topKFrequent(int[] nums, int k) {
        //使用hashMap<个数,数据>
        //个数数组,[3,2,1]...
        int n = nums.length;
        //大于k的肯定在里面 (n+k-1)/k
        HashMap<Integer, Integer> hash = new HashMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
//                return o2[1] - o1[1];//大根堆
                return o1[1] - o2[1];
            }
        });
        for (int i = 0; i < n; i++) {
            Integer c = hash.getOrDefault(nums[i], 0);
            hash.put(nums[i],c+1);
        }
        int i = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = hash.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            if(i<k)
                queue.offer(new int[]{next.getKey(),next.getValue()});
            else{
                if(queue.peek()[1]<next.getValue()){
                    queue.poll();
                    queue.offer(new int[]{next.getKey(),next.getValue()});
                }
            }
            i++;
        }
        i = 0;
        int[] res = new int[k];
        for (int[] q : queue) {
            res[i++] = q[0];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,0,1,0};
        int k = 1;
        int[] ints = new Solution69().topKFrequent(nums, k);
    }
}
