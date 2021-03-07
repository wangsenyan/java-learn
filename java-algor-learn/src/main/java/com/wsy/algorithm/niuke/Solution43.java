package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.Comparator;

class Solution43 {
    public int maxEnvelopes(int[][] envelopes) {
        //先以w排序,再以h排序
        int len = envelopes.length;
        if(len<2) return len;
        int[][] env = new int[len][3];
        for(int i=0;i<len;i++){
            env[i]=new int[]{envelopes[i][0], envelopes[i][1], envelopes[i][0] + envelopes[i][1]};
        }
        int[] count = new int[len];
        Arrays.fill(count,1);
        Arrays.sort(env, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int max = 1;
        for(int i=0;i< env.length; i++){
            int w = env[i][0];
            int h = env[i][1];
            for(int j=i-1;j>=0;j--){
                if(env[j][0]< w && env[j][1]< h) {
                    count[i] = Math.max(count[j]+1,count[i]);
                    max = max < count[i] ? count[i] : max;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][]matrix = {{6,10},{11,14},{6,1},{16,14},{13,2}};
        System.out.println(new Solution43().maxEnvelopes(matrix));
    }
}