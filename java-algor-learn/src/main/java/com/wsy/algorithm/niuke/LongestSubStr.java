package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStr {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int mx = 0;
        for (int i = 0,k=0; i < len; i++) {
            int c = s.charAt(i);
            int cx = 0;
            for(int j=k;j<=i;j++){
                int g = s.charAt(j);
                if(g!=c||i==j)
                    cx++;
                else{
                    k=j+1;
                    break;
                }
            }
            mx=mx<cx?cx:mx;
        }
        return mx;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubStr().lengthOfLongestSubstring("aabbccdd"));
    }
}