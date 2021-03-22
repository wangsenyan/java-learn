package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.HashSet;

public class Solution66 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        //队列 pq[index,nums]
        //寻找第k/2,(k+1)/2大的数字 [-1,1,3]
        //每次加入,大于中位数,那么右移一位 加入-3,删除-1,中位数为
        //小于中位数,左移一位
        int n = nums.length;
        int[] sort = new int[k];
        double[] ans  = new double[n-k+1];
        for (int i = 0; i < n-k+1; i++) {
            for (int j = 0; j < k; j++) {
                sort[j]=nums[j+i];
            }
            Arrays.sort(sort);
            if(k%2==0){
                ans[i] = (sort[k/2-1] + sort[k/2 +1])/2.0;
            }else{
                ans[i]= (double) sort[k/2];
            }
        }
        return ans;
    }
}
