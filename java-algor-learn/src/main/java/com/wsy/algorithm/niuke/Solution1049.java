package com.wsy.algorithm.niuke;

public class Solution1049 {
    public int lastStoneWeightII(int[] stones) {
        //[2,7,4,1,8,1],相减顺序
        //均匀分成两份
        int sum = 0;
        int n = stones.length;
        for (int stone : stones) {
            sum += stone;
        }
        //背包最大sum/2;
        int sz = sum/2;
        int[] bag = new int[sz+1];
        //0/1背包问题,求差值
        for (int i = 0; i < n; i++) {
            int w = stones[i];
            for (int k = sz; k >= w ; k--) {
                bag[k] = Math.max(bag[k],bag[k-w] + w);
            }
        }
        return sum - 2 * bag[sz];
    }

    public static void main(String[] args) {
        int[] stons = {2,7,4,1,8,1};
        int i = new Solution1049().lastStoneWeightII(stons);
        System.out.println(i);
    }
}
