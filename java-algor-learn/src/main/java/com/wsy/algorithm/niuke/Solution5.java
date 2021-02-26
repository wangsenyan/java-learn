package com.wsy.algorithm.niuke;

import java.util.HashMap;

public class Solution5 {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer, Integer> hash = new HashMap<>();
        int i=0;
        for (; i < len; i++) {
            if(hash.containsKey(target - nums[i]))
                break;
            hash.put(nums[i],i);
        }
        return new int[]{i,hash.get(target-nums[i])};
    }

    public static void main(String[] args) {
        int[] ns = new int[]{3,2,4};
        int[] ints = new Solution5().twoSum(ns, 6);
        for (int i = 0; i <2 ; i++) {
            System.out.println(ints[i]);
        }
    }
}
