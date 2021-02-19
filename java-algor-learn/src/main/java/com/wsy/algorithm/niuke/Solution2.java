package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution2 {
    int equalSubstring(String s, String t, int maxCost) {
        //差值排序后选取
        int len = s.length();
        int[] r = new int[len];
        for(int i=0;i < len; i++){
           int rr = s.charAt(i) - t.charAt(i);
           r[i] = rr>0?rr:-rr;
        }
        //int[] m = new int[len];
        int max = 0;
        int sum = 0;
        for (int i=0,j=0; i < len;) {
            for(;j<len;j++){
                if(sum + r[j] > maxCost)
                    break;
                sum += r[j];
            }
            max = max < j- i?j-i:max;
            sum-=r[i];
            i++;
            //减前面一个,后面增加几个
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().equalSubstring("krrgw", "zjxss", 19));
    }
}
