package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.Comparator;

class Solution99 {
    public int storeWater(int[] bucket, int[] vat) {
        int n = bucket.length;

        int time = 0;
        //到向下取整的次数,整体和局部的协调
        int[][] sort = new int[n][2];
        for(int i=0;i<n;i++){
            if(bucket[i]==0 && vat[i]>0){
                time++;
                bucket[i]++;
            }
            int ut = (vat[i] + bucket[i] - 1)/bucket[i];
            sort[i][0] = ut;
            sort[i][1] = i;
        }
        Arrays.sort(sort, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        int subTime = sort[0][0];
        int leftValue = sort[0][0];
        while (subTime >1){
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int subIndex = sort[i][1];
                if( subTime > 1 && subTime == leftValue){
                    //计算下降一位的成本
                    int i1 = (vat[subIndex] + subTime - 2) / (subTime - 1);//下一次需打几桶水?
                    int down = i1 - bucket[subIndex];
                    sort[i][0] = subTime - 1;
                    bucket[subIndex] = i1;
                    sum+=down;
                }
            }
            if(sum <= n){//增加桶容量有益处
                time+=sum;
                subTime = subTime -1;
                leftValue = sort[0][0];
            }else
                break;
        }
        //每次最大的升级到减一次蓄水 与 加满水比较大小
        return  time;
    }
}