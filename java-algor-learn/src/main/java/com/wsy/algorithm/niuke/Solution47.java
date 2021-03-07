package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.BiFunction;

public class Solution47 {
    public String minWindow(String s, String t) {
        //队列仅仅保存在t中的字符及位置
        int sl = s.length();
        int tl = t.length();
        int[] patten = new int[128];
        int[] window = new int[128];
        String res = "";
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < tl; i++) {
            patten[t.charAt(i)]++;
        }
        int left=0,right=0,count=0;
        while (right < sl){
            char c = s.charAt(right);
            window[c]++;
            if(patten[c]>0 && window[c] <=patten[c]){
                count++;
            }
            while (count == tl){
                if(right - left + 1 < min){
                    min = right - left + 1;
                    res = s.substring(left,right+1);
                }
                char c1 = s.charAt(left);
                if(window[c1]>0 && window[c1]<=patten[c1]){
                    count--;//保证能跳出去
                }
                window[c1]--;
                left++;
            }
            right++;
        }
        return res;
    }
    public static void main(String[] args) {
        String s ="ADOBBBECODEBAVNCC"
                ,t ="ABC";
        String s1 = new Solution47().minWindow(s, t);
        System.out.println(s1);
    }
}
