package com.wsy.algorithm.niuke;

import java.util.Arrays;

public class Solution61 {
        public int numDistinct(String s, String t) {
            int m = s.length();
            int n = t.length();
            int dp[][] = new int[n + 1][m + 1];
            Arrays.fill(dp[0], 1);
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (t.charAt(i - 1) == s.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                    else
                        dp[i][j] = dp[i][j - 1];
                }
            }
            return dp[n][m];
        }

    public static void main(String[] args) {
        int i = new Solution61().numDistinct("babgbag", "bag");
    }
}
