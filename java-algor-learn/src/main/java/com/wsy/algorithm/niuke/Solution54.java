package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Solution54 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //[0,2][3,5]
        //stack 1 3 -1
        //入栈
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            while(!queue.isEmpty() && nums[queue.getLast()]<nums[i]){
                queue.removeLast();
            }
            queue.addLast(i);
            if(queue.getFirst()<=i-k)
                queue.removeFirst();
            if(i>=k-1)
                res.add(nums[queue.getFirst()]);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
