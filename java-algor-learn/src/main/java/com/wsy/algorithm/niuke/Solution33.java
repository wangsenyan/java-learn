package com.wsy.algorithm.niuke;

public class Solution33 {
    public int[] countBits(int num) {
        //最多31位
        int n = num;
        int[] ans = new int[num+1];
        int len=1;
        while ((num>>=1) >0) len++;
        ans[0] = 0;
        for(int i=0;i<=len;i++){
            int hl = 1<<i;
            int hh = hl<<1;
            for(int j=hl;j<hh && j<=n ;j++){
                ans[j] = 1 + ans[j-hl];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new Solution33().countBits(0);
    }
}
