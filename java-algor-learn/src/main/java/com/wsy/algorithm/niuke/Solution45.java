package com.wsy.algorithm.niuke;

class Solution45 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][]dp = new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i = 1 ;i<= m;i++){
            for(int j = 1; j<=n; j++){
                char _c = s.charAt(i-1);
                char _p = p.charAt(j-1);
                if(_p == _c || _p == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if( _p == '*'){
                    if(p.charAt(j-2)=='.')
                        dp[i][j] = dp[i-1][j-1] || dp[i-1][j] || dp[i][j-1];
                    else
                        dp[i][j] = dp[i][j-2];

                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        boolean aa = new Solution45().isMatch("aa", "a*");

    }
}