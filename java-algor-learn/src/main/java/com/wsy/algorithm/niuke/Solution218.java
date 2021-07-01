package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        LinkedList<int[]> pdd = new LinkedList<>();
        for (int[] build : buildings) {
            pdd.add(new int[]{build[0],-build[2]});
            pdd.add(new int[]{build[1],build[2]});
        }
        pdd.sort((a,b)->a[0] == b[0] ? a[1] - b[1]: a[0] -b[0]);
        int m = 0;
        PriorityQueue<Integer> hq = new PriorityQueue<>((a,b)->b-a);
        hq.offer(0);
        List<List<Integer>> res = new ArrayList<>();
        while (!pdd.isEmpty()){
            int[] build = pdd.removeFirst();
            int key = build[0];
            int val = build[1];
            if(val < 0) hq.add(-val);
            else hq.remove(val);
            int left = hq.peek();
            if(m != left){
                res.add(Arrays.asList(key,left));
            }
            m = left;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution218 solution218 = new Solution218();
        int[][] buildings = {{0,2,3},{2,5,3}};
        List<List<Integer>> skyline = solution218.getSkyline(buildings);
        System.out.println(skyline);
    }
}
