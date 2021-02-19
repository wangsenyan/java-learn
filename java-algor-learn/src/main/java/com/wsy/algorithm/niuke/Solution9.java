package com.wsy.algorithm.niuke;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
public class Solution9 {

    private String s;
    private String p;
    private ExecutorService service;
    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        return isMatchOfIndex(0,0);
    }
    private boolean isMatchOfIndex(int i,int j){
        if(j >= p.length())  return i>=s.length();
        boolean match = i<s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if(j<p.length() -1 && p.charAt(j+1) == '*'){
            return isMatchOfIndex(i,j+2) || match && isMatchOfIndex(i+1,j);
        }else
            return match && isMatchOfIndex(i+1,j+1);
    }

    private boolean isMatch1(String s,String p){
        s = " "+s;
        p = " "+p;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    if(sc!=p.charAt(j-2) && p.charAt(j-2)!='.'){
                        dp[i][j] = dp[i][j-2];
                    }else{
                        //sc == p.charAt(j-2) 或 p.charAt(j-2)=='.'
                        dp[i][j] = dp[i][j-1] || dp[i][j-2] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution9().isMatch1("aab", "c*a*b"));
    }
}
