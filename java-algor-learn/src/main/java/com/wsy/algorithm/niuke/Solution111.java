package com.wsy.algorithm.niuke;

public class Solution111 {
    public int countDigitOne(int n) {
        int i = 1, num = n, a = 0;
        int l = 0;
        while (num > 0){
           int u = num % 10;
           if(u==1) a+= (n%i) + l + 1;
           if(u>1) a+= u * l  + i;
           num/=10;
           l = l * 10 + i;
           i *= 10;
        }
        return a;
    }

    public static void main(String[] args) {
        new Solution111().countDigitOne(90876);
    }
}
