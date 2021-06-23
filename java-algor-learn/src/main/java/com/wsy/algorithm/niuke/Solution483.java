package com.wsy.algorithm.niuke;

public class Solution483 {
    public String smallestGoodBase(String n) {
        long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
//            int k = Math.pow(Math.log(nVal-1)/2,2)
            int k = (int) Math.pow(nVal, 1.0 / m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }
    public static void main(String[] args) {
        String n = "1000000000000000000";
        String s = new Solution483().smallestGoodBase(n);
        System.out.println(s);
    }
}
