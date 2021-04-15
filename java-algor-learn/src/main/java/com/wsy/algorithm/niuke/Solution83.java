package com.wsy.algorithm.niuke;

import java.util.Arrays;

class Solution83 {
    public int minJump(int[] jump) {
        //遍历获取每个节点可以到达的位置
        int n = jump.length;
        int[] step = new int[n+1];
        Arrays.fill(step,Integer.MAX_VALUE/2);
        step[n] = 1;
        for(int i=n-1;i>=0;i--){
            for(int k=i-1;k>=0;k--){
                if(jump[k] + k >=n) step[k+1] = 1;
                else if(jump[k] + k==i){
                    step[k+1] = Math.min(step[k+1],step[i+1] + 1);
                }
            }
        }
        //从0到i的最小次数
        for(int i = 0; i< n ;i++){
            for(int k= jump[i]+i;k>=0;k--){
                step[i+1] = Math.min(step[i+1],step[k+1] + 1);
            }
        }
        return step[1];
    }

    public static void main(String[] args) {

        int[] jump = new int[]{2,5,1,1,1};
        System.out.println(new Solution83().minJump(jump));

    }
}