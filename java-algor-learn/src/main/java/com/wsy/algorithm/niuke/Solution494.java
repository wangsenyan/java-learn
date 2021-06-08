package com.wsy.algorithm.niuke;

class Solution494 {
    //完全背包问题
    //nums选出子集和为sz的个数
    public int findTargetSumWays(int[] nums, int target) {
        int len = nums.length;
        int sum = 0;
        for(int num : nums){
            sum+=num;
        }
        if((sum - target) % 2!=0 || sum < target)
            return 0;
        int sz = (sum - target)/2;//背包大小
        int[] bag = new int[sz+1];
        bag[0] = 1;
        boolean[] seen = new boolean[sz+1];
        seen[0] = true;
        for(int i = 0 ; i < len; i++){
            int num = nums[i];
            for(int j = sz; j >= num; j--){
                if(seen[j-num]){
                    bag[j]+=bag[j-num];
                    seen[j]=true;
                }
            }
        }
        return bag[sz];
    }

    public static void main(String[] args) {
        int[] nums = {2,107,109,113,127,131,137,3,2,3,5,7,11,13,17,19,23,29,47,53};
        int target = 1000;
        int targetSumWays = new Solution494().findTargetSumWays(nums, target);
        System.out.println(targetSumWays);
    }
}