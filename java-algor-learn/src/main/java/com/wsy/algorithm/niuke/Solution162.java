package com.wsy.algorithm.niuke;

import java.util.HashSet;
import java.util.Set;

public class Solution162 {
    //超时
    public int consecutiveNumbersSum1(int N) {
        long sum = 0;
        int res = 0;
        Set<Long> map = new HashSet<>();
        map.add(0L);
        for (int i = 1; i <= N; i++) {
           sum += i;
           if(map.contains(sum - N)){
               res++;
           }
           map.add(sum);
        }
        return res;
    }
    //数字法
    //设从i开始,K个数字, N = (x + 1) + (x + 2) + ... + (x + k)
    //                N = kx + 1/2 * k * (k +1) --> 2N = 2kx + k * (k + 1) 两个变量k,x
    public int consecutiveNumbersSum(int N) {
        int res = 0;
        for (int k = 1; k <= Math.sqrt(2 * N); k++) {
            if((2 * N - k * (k + 1 ) )% (2 * k) == 0){
                res++;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int N = 5;
        int i = new Solution162().consecutiveNumbersSum(N);
        System.out.println(i);
    }
}
