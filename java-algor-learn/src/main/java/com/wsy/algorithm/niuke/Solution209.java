package com.wsy.algorithm.niuke;

public class Solution209 {
    public int minSubArrayLen(int target, int[] nums) {

        int n = nums.length;
        int r = n+1;
        //滑动窗口
        int win = 0;
        for(int i = 0 , j = 0; i < n; i++){
            win += nums[i];
            while(win>=target){
                r = Math.min(i-j+1,r);
                if(r==1) return 1;
                win-=nums[j++];
            }
        }
        return r==n+1?0:r;
    }

    public static void main(String[] args) {
        int target= 15;
        int[] nums = {1,2,3,4,5};
        int i = new Solution209().minSubArrayLen(target, nums);
        System.out.println(i);
    }
}
