package com.wsy.algorithm.niuke;

import java.util.Arrays;

public class Solution64 {
    public int maxResult(int[] nums, int k) {

        //start + [start+1,start+k] max
        int n = nums.length;
        int start = 0;
        int res = nums[0];
        while(start<n-1){
            int next = start+1;
            int max = Integer.MIN_VALUE;
            for(int i=start+1;i<=start+k && i<n;i++){
                int sub = res + nums[i];
                if(sub>=max){
                    max = sub;
                    next = i;
                }
            }
            res = max;
            start = next;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,-1,-2,4,-7,3};
        int k = 2;
        int i = new Solution64().maxResult(nums, k);
    }
}
