package com.wsy.algorithm.niuke;

import java.util.Arrays;

public class Solution21 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        //滑动窗口
        int max=0,cur=0,ans=0,idx=0;
        int len = customers.length;
        for(int i=0,s=0;i<len;i++){
            //i天可能生气或不生气
            while(s<X && i+s<len){
                cur+=customers[i+s];
                s++;
            }
            if(grumpy[i]==1 && max < cur){
                idx=i;
                max=cur;
            }
            cur-=customers[i];
            s--;
        }
        for(int i=idx;i<idx+X && i<len;i++){
            grumpy[i] = 0;
        }
        for(int i=0;i<len;i++){
            if(grumpy[i]==0)
                ans+=customers[i];
        }
        return ans;
    }

    public static void main(String[] args) {
       int[] customers = {4,10,10};
       int[] grumpy = {1,1,0};
       int X = 2;
        System.out.println(new Solution21().maxSatisfied(customers, grumpy, X));
    }
}
