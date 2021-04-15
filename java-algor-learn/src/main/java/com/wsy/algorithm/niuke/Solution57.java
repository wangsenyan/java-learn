package com.wsy.algorithm.niuke;

public class Solution57 {
    public boolean isMatch(String s, String p) {
          s = " "+s;
          p = " "+p;
            int m = s.length();
            int n = p.length();
            boolean[][]dp = new boolean[m+1][n+1];
            dp[0][0]=true;
            for(int i=1;i<=m;i++){
                char sc=s.charAt(i-1);
                for(int j=1;j<=n;j++){
                    char pc=p.charAt(j-1);
                    if(sc==pc || pc=='?'){
                        dp[i][j]=dp[i-1][j-1];
                    }else if(pc == '*'){
                        dp[i][j]=dp[i-1][j]||dp[i][j-1]||dp[i-1][j-1];
                    }
                }
            }
            return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "";
        String p = "******";
        boolean match = new Solution57().isMatch(s, p);

    }
}
