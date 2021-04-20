package com.wsy.algorithm.niuke;

public class Solution116 {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) {
            return haystack.length() == 0 ? 0 : -1;
        }
       return compare(haystack,needle);
    }
    private int[] next(String p){
        int n = p.length();
        int i = -1 ,j = 0;
        int[] next = new int[n];
        next[0] = -1;
        while(j < n - 1){
            if(i == -1 || p.charAt(i) == p.charAt(j)){
                i++;
                j++;
                next[j] = i;
            }
            else
                i = next[i];
        }
        return next;
    }
    private int compare(String s,String p){
        int sl = s.length();
        int pl = p.length();
        int i = 0 ,j = 0;
        int[] next = next(p);
        while (i < sl && j < pl){
            if( j == -1 || s.charAt(i ) == p.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        if(j == pl) return i;
        else return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution116().next("aabbccaabb"));
    }
}
