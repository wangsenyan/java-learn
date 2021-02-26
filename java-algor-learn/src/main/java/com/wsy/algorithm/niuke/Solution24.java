package com.wsy.algorithm.niuke;

public class Solution24 {
    public String longestDupSubstring(String s) {
        int[] next = new int[s.length() + 1];
        int i=-1,j = 0;
        next[0] = -1;
        while(j<s.length()){
            if(i==-1 || s.charAt(i) == s.charAt(j)){
                i++;
                j++;
                next[j]=i;
            }else{
                i = next[i];
            }
        }
        return "";
    }

    public static void main(String[] args) {
        new Solution24().longestDupSubstring("banana");
    }
}
