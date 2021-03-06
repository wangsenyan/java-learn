package com.wsy.algorithm.niuke;

public class Solution166 {
    public int superEggDrop(int k, int n) {
        //dp[k][n] = 1 + min(max(dp[k-1][x-1],dp[k][n-x]))
        int[][] dp = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int l = 2; l <= k; l++) {
                int res = Integer.MAX_VALUE;
                for (int j = 1; j <= i; j++) {
                    res = Math.min(res , Math.max(dp[j-1][l-1], dp[i-j][l]) + 1);
                }
                dp[i][l] = res;
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        System.out.println(new Solution166().superEggDrop(2, 6));
    }
}
