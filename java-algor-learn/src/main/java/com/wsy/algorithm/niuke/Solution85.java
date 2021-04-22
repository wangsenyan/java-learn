package com.wsy.algorithm.niuke;

import java.util.*;

class Solution85 {
    private Map<Integer, LinkedList<Integer>> map;
    public int minJumps(int[] arr) {
        //使用广度遍历
        map = new HashMap<>();
        int n = arr.length;
        boolean[] seen = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();
        int step = 0;
        for (int i = 0; i < n; i++) {
            LinkedList<Integer> ls = map.getOrDefault(arr[i], new LinkedList<>());
            ls.addFirst(i);
            //ls.addLast(i);
            map.put(arr[i],ls);
        }
        queue.add(0);
        seen[0] = true;
        while(!queue.isEmpty()){
            //可加入i+1,i-1,map[i]
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Integer idx = queue.removeFirst();
                if(idx == n-1) return step;
                if(idx-1>=0 && !seen[idx-1]) {
                    queue.add(idx - 1);
                    seen[idx-1] = true;
                }
                if(idx+1<n && !seen[idx+1]) {
                    queue.add(idx + 1);
                    seen[idx+1] = true;
                }
                List<Integer> ls = map.get(arr[idx]);
                Iterator<Integer> iterator = ls.iterator();
                while (iterator.hasNext()){
                    Integer next = iterator.next();
                    if(!seen[next]){
                        queue.add(next);
                        seen[next]=true;
                        iterator.remove();
                    }
                }
            }
            step++;
        }
        return step;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int[] nums = new int[50000];
        for (int i = 0; i < 50000; i++) {
            nums[i]=7;
        }
        nums[50000-1] = 11;
        int i = new Solution85().minJumps(nums);
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
        System.out.println(i);
    }
}