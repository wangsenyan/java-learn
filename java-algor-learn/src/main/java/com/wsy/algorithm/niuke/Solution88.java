package com.wsy.algorithm.niuke;

class Solution88 {
    public int longestCommonSubsequence(String text1, String text2) {
        //对i,j,text1[i]==text[j] 都前进或者,否则text1前进,text2前进或不前进
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i <= n; i++){
            char c1 = text2.charAt(i-1);
            for(int j = 1; j <= m; j++){
                char c2 = text1.charAt(j-1);
                if(c1 == c2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    // dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]) + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1],Math.max(dp[i-1][j],dp[i-1][j-1]));
                }
            }
        }
        return dp[n][n];
    }

    public static void main(String[] args) {

        String text1 = "ezupkr";
        String text2 = "ubmrapg";
        int i = new Solution88().longestCommonSubsequence(text1, text2);

    }
}