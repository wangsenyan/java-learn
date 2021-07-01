package com.wsy.algorithm.niuke;

import java.util.Arrays;

public class Solution274 {

    public int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);
        int l = 0, r = n-1;
        while (l < r){
            int m = (l + r) >> 1;
            int k = n - m ;
            if(k <= citations[m])
                r = m;
            else
                l = m + 1;
        }
        return n - l <= citations[l] ? n - l : 0;
    }
}
