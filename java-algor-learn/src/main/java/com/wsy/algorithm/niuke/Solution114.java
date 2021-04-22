package com.wsy.algorithm.niuke;

public class Solution114 {
    /**
     * 鸡蛋掉落 dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1
     * @param k 鸡蛋个数
     * @param n 楼层
     */
    public int superEggDrop1(int k, int n) {
        int[][] dp = new int[k+1][n+1];
        int m = 0;
        for ( ;dp[k][m] < n;) {
            m++;
            for (int i = 1; i <= k; i++)
                dp[i][m] = 1 + dp[i-1][m-1] + dp[i][m-1];
        }
        return m;
    }
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
        int i = new Solution114().superEggDrop(8, 10000);
        System.out.println(i);
    }
}
