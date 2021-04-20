package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution110 {
    private int[] nums;
    private boolean valid;
    //dfs超时
    public boolean canPartition1(int[] nums) {
        //先求和,然后平分
        //这个数在或不在集合
        Arrays.sort(nums);
        this.nums = nums;
        this.valid = false;
        int n = nums.length;
        int sum = 0;
        for(int i = 0 ; i < n;i++){
            sum+=nums[i];
        }
        if(sum%2!=0) return false;

        dfs(n - 1,sum/2);
        return valid;
    }
    private void dfs(int i,int sum){
        //有这个数字
        if(sum == 0){
            valid = true;
            return;//不再继续
        }
        //已经成功,已经末尾,后面的数超出限制
        if(valid || i< 0 || sum<0)
            return;//不再继续
        //包含这个值
        dfs(i-1,sum - nums[i]);
        dfs(i-1,sum);

        //不包含这个值
    }

    public static void main(String[] args) {
        int[] arrs = {100,100,100,100,100,97,99,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        boolean b = new Solution110().canPartition(arrs);
    }

    // 完全背包问题
    // 01背包问题
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int m : nums) {
            sum+=m;
        }
        if((sum&1)!=0) return false;
        int target = sum/2;
        int n = nums.length;
        boolean[] bag = new boolean[target+1];
        bag[0] = true;
        if(nums[0] <= target) bag[nums[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = target; j>=0; j--) {
                if(nums[i]<=j){
                    bag[j] = bag[j] || bag[j-nums[i]];
                }
            }
            if(bag[target]) return true;
        }
        return bag[target];
    }
}
