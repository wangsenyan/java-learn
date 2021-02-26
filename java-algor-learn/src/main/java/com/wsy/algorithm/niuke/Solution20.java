package com.wsy.algorithm.niuke;

public class Solution20 {
    public String shortestPalindrome(String s) {
        String str = new StringBuffer(s).reverse().toString();
        String sNew = s + "#" + str;
        int i= -1,j=0;
        int[] next = new int[sNew.length() + 1];
        next[0] = -1;
        while(j<sNew.length()){
            if(i==-1 || sNew.charAt(i) == sNew.charAt(j)){
                i++;
                j++;
                next[j] = i;
            }else{
                i = next[i];
            }
        }
        return sNew.substring(s.length()+1, sNew.length() - next[sNew.length()]) + s;
    }

    public static void main(String[] args) {
        String s = "aacecaaa";
        System.out.println(new Solution20().shortestPalindrome(s));
    }
}
