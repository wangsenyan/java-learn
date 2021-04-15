package com.wsy.algorithm.niuke;

import java.util.Arrays;

class Solution84 {

    public int coinChange(int[] coins, int amount) {
        //贪心算法
        Arrays.sort(coins);
        int n = coins.length;
        int[] f = new int[amount + 1];
        Arrays.fill(f,Integer.MAX_VALUE/2);
        f[0] = 0;
        for (int i = 1; i <=amount; i++) {
            for (int j = 0; j < n ; j++) {
                if(i-coins[j]>=0){
                    f[i] = Math.min(f[i],f[i-coins[j]]) + 1;
                }
            }
        }
        return f[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{186,419,83,408};
        int amount = 6249;
        System.out.println(new Solution84().coinChange(coins, amount));
    }
}