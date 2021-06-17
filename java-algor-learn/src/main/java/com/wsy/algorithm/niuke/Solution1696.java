package com.wsy.algorithm.niuke;

import java.util.LinkedList;

public class Solution1696 {
    public int maxResult(int[] nums, int k) {
        //题目特点:1.局部范围与整体的协调
        //不同的想法有不同的解法
        //解法一:队列,局部最大值,如果 在[i-k,i] 寻找最大值,如果大,从队首删,否则加入队尾,
        int n = nums.length;
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{nums[0],0});//[和,位置]
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            while (i - q.getFirst()[1] > k )
                q.removeFirst();
            ans = q.getFirst()[0] + nums[i];
            while (!q.isEmpty() && q.getLast()[0] <= ans){
                q.removeLast();
            }
            q.add(new int[]{ans,i});
        }
        return ans;
    }

    public static void main(String[] args) {
        int[]nums = new int[]{-1,-2,-3};
        int k = 1;
        System.out.println(new Solution1696().maxResult(nums, k));

    }
}
