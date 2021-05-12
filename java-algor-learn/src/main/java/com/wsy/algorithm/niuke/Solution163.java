package com.wsy.algorithm.niuke;

import java.util.Arrays;

public class Solution163 {
    //回溯法
    public int minimumTimeRequired1(int[] jobs, int k) {
        int n = jobs.length;
        Arrays.sort(jobs);

        int l = 0,r = n - 1;
        //按降序排列
        while (l < r){
          int temp = jobs[l];
          jobs[l++] = jobs[r];
          jobs[r--] = temp;
        }
        //获取区间
        int low = jobs[0];
        int high = Arrays.stream(jobs).sum();
        while (low < high){
            int mid = (low + high) >> 1;
            //判断给定的箱子大小是否满足要求
            if(trans(mid,k,jobs)){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return low;

    }
    public boolean trans(int box,int k,int[] jobs){
        //依次分配给k个人
        int[] each = new int[k];

        return check(0 ,box ,each,jobs);
    }
    //将第i个job分配给第j个人
    public boolean check(int i,int box,int[] each,int[] jobs){
        //分配完了
        if(i >= jobs.length )
            return true;
        int job = jobs[i];
        int m = each.length;
        for (int k = 0; k < m; k++) {
            if(each[k] + job <= box){
                each[k] += job;
                if(check(i+1,box,each,jobs))
                   return true;
                each[k] -= job;
            }
            //特殊情况,工人k满工或旷工都不能,说明方案不可行
            if(each[k] == 0 || each[k] + job == box)
                break;
        }
        return false;
    }

    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int[] sum = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            int x = Integer.numberOfTrailingZeros(i), y = i - (1 << x);
            sum[i] = sum[y] + jobs[x];
        }

        int[][] dp = new int[k][1 << n];
        for (int i = 0; i < (1 << n); i++) {
            dp[0][i] = sum[i];
        }

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < (1 << n); j++) {
                int minn = Integer.MAX_VALUE;
                for (int x = j; x != 0; x = (x - 1) & j) {
                    minn = Math.min(minn, Math.max(dp[i - 1][j - x], sum[x]));
                }
                dp[i][j] = minn;
            }
        }
        return dp[k - 1][(1 << n) - 1];
    }
    public static void main(String[] args) {
        int[] jobs = {3,2,3,4,5,6,7,8,9,10,12,7};
        int k = 4;
        int i = new Solution163().minimumTimeRequired(jobs, k);
        System.out.println(i);
    }
}
