package com.wsy.algorithm.niuke;

import java.util.Arrays;

class Solution93 {
    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int sum = 0;
        int left = 0;
        int right = n -1;
        while(left<right){
            if(nums[left]+nums[right]<=target) {
                sum += right - left;
                ++left;
            }else{
                --right;
            }
        }
        return sum % 1000000007;
    }
    public int purchasePlans1(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(nums[i] + nums[j] <=target)
                   sum++;
            }
        }
        return sum % 1000000007;
    }

    public static void main(String[] args) {
        int[] nums = {2,5,3,5,5,6,7,7,1};
        int target =14 ;
        Solution93 solution93 = new Solution93();
        int i = solution93.purchasePlans(nums, target);
        int i1 = solution93.purchasePlans1(nums, target);
        System.out.println(i1);
    }
}