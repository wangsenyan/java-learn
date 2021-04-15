package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

class Solution79 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        //方法一；暴力
        //方法二；动态规划
        // dp[i] = Math.max(dp[i]) 包含0 和正负号数量,遇到0,分割,负号,记下,左边 * 右边 * 负号
        //找出负号多出的那个,可保证最大
        long[] mut = new long[n];//乘法
        for(int i = 0;i < n;i++){
            if(i==0 || mut[i-1]==0) mut[i] = nums[i];
            else if(nums[i]==0){
                mut[i]=0;
            }else {
                mut[i] = mut[i-1] * nums[i];
            }
        }
        int right = n - 1;
        int left = n - 1;
        long max = Long.MIN_VALUE;
        while(left>=0){
            List<Integer> arr = new ArrayList<>();
            while(left>=0 && mut[left]!=0){
                if(nums[left]<0) arr.add(left);
                left--;
            }
            if(arr.size()%2 == 1 && right!= left + 1){
                for (Integer index : arr) {
                    if(index>left+1) max = Math.max(mut[index]/nums[index],max);
                    if(index<right) max = Math.max(mut[right]/mut[index],max);
                }
            }
            if(left>=0) max = Math.max(0,max);
            max = Math.max(mut[right],max);
            left--;
            right = left ;

        }
        return (int)max;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{-2,0,-1};
        System.out.println(new Solution79().maxProduct(nums));
    }
}