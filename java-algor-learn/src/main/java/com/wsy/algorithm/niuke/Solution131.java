package com.wsy.algorithm.niuke;

public class Solution131 {
    public int countSubstrings(String s) {
        //反转后拼接
        //以某个为中心
        int n = s.length();
        int total = 0;
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (i-j>=0 && i+j<n && s.charAt(i-j)==s.charAt(i+j)){
                j++;
                total++;
            }
            j = 0;
            while(i-j-1>=0 && i+j<n && s.charAt(i-j-1)==s.charAt(i+j)) {
                j++;
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        String s = "aaa";
        int i = new Solution131().countSubstrings(s);
    }
}
