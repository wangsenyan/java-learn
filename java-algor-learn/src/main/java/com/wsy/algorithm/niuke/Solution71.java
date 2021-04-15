package com.wsy.algorithm.niuke;

import java.util.Arrays;

/**
 * Solution72
 */
class Solution71 {
    private boolean[]  ac;
    private int[] height;
    private int[] arr;
    public int maxJumps(int[] arr, int d) {
        //从最小开始
        //每个位置的最小值
        //从最小往最大
        //递归的话 f(i) = max(f(i))
        int n = arr.length;
        height = new int[n];
        ac = new boolean[n];
        this.arr = arr;
        Arrays.fill(height,1);
        int max = 1;
        for(int i=0;i<n;i++){
            max = Math.max(max,helper(i,n,d));
        }
        return max;
    }
    private int helper(int i,int n,int d){
        if(ac[i]) return height[i];
        int l = i - d > 0 ? i - d : 0;
        int r = i + d < n -1  ? i + d : n -1;
        int lmax = 0;
        int rmax = 0;
        for(int k = i -1 ;k >= l;k--){
            if(arr[k]<arr[i]){
                height[k] = helper(k,n,d);
                ac[k] = true;
                lmax = Math.max(height[k],lmax);
            }else{
                break;
            }
        }
        for(int k = i+1;k <= r;k++){
            if(arr[k]<arr[i]){
                height[k] = helper(k,n,d);
                ac[k] = true;
                rmax = Math.max(height[k],rmax);
            }else{
                break;
            }
        }
        height[i] = Math.max(lmax,rmax) + 1;
        ac[i] = true;
        return height[i];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,6,5,4,3,2,1};
        int d  = 1;
        int i = new Solution71().maxJumps(nums, d);
        System.out.println(i);
    }
}