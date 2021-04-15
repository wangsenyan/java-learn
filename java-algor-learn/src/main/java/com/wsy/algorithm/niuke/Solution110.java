package com.wsy.algorithm.niuke;

public class Solution110 {
    private int[] nums;
    private boolean valid;
    public boolean canPartition(int[] nums) {
        //先求和,然后平分
        //这个数在或不在集合
        this.nums = nums;
        this.valid = false;
        int n = nums.length;
        int sum = 0;
        for(int i = 0 ; i < n;i++){
            sum+=nums[i];
        }
        if(sum%2!=0) return false;

        dfs(0,sum/2);
        return valid;
    }
    private void dfs(int i,int sum){
        //有这个数字
        if(sum == 0){
            valid = true;
            return;
        }
        if(i==nums.length)
            return;
        //包含这个值
        dfs(i+1,sum - nums[i]);
        //不包含这个值
        dfs(i+1,sum);
    }
}
