package com.wsy.algorithm.niuke;

import java.util.LinkedList;

class Solution73 {
    public boolean find132pattern(int[] nums) {
        //非单调就是
        //单调栈,内部有两个,然后外部小于内部2
        LinkedList<Integer> stack = new LinkedList<>();
        int n = nums.length;
        for(int i=0;i<n;i++){
            int lnum = 0;
            while(!stack.isEmpty() && nums[stack.getLast()] >= nums[i]){
                stack.removeLast();
                if(nums[stack.getLast()]==nums[i]) lnum++;
            }
            stack.add(i);
            if(stack.size()>1 && stack.get(stack.size()-1)!=stack.get(stack.size()-2) + 1)
                if(stack.getLast() - stack.get(stack.size() -2)>lnum+1)
                   return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,0,0,1,1,0,1,0,1,0};
        boolean pattern = new Solution73().find132pattern(nums);
    }
}