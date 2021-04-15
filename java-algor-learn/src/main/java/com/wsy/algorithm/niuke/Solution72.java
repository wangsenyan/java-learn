package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution72 {
    public int maxJumps(int[] arr, int d) {
        //从最小开始
        //每个位置的最小值
        //从最小往最大
        //递归的话 f(i) = max(f(i))
        int n = arr.length;
        int[][] cp = new int[n][2];
        for (int i = 0; i < n; i++) {
            cp[i][0] = arr[i];
            cp[i][1] = i;
        }
        Arrays.sort(cp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int max = 1;
        int[] height = new int[n];
        Arrays.fill(height,1);
        for (int i = 0; i < n; i++) {
            int  index = cp[i][1];
            int l = Math.max(index - d, 0);
            int r = Math.min(index + d, n - 1);
            int lmax = 0;
            int rmax = 0;
            for (int k = index - 1; k >= l; k--) {
                if(arr[k] < arr[index]){
                    lmax = Math.max(lmax,height[k]);
                }
            }
            for (int k = index + 1; k <= r; k++) {
                if(arr[k] < arr[index]){
                    rmax = Math.max(lmax,height[k]);
                }
            }
            height[index] = Math.max(lmax,rmax) +1;
            max = Math.max(height[index],max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{6,4,14,6,8,13,9,7,10,6,12};
        int d  = 2;
        int i = new Solution72().maxJumps(nums, d);

        System.out.println(i);
    }
}
