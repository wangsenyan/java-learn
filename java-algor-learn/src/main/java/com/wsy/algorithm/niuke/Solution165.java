package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution165 {
    private boolean valid;
    Map<Integer, Set<Integer>> map;
    public boolean canCross1(int[] stones) {
        //上一个可以过河,那么
        //数据结构,记录到达位置i的k
        this.map = new HashMap<>();
        int n = stones.length;
        for (int i = 0; i < n; i++) {
            map.put(stones[i],new HashSet<>());
        }
        map.get(stones[0]).add(1);
        dfs(0, stones[n-1],0);
        return valid;
    }

    /**
     *
     * @param f 跳到位置i
     * @param k 上一次跳的次数
     * @return
     */
    private void dfs(int f,int e,int k){
        //k-1,k,k+1
        if(f == e){
            valid = true;
            return;
        }
        for (int i = Math.max(1, k-1); i <= k+1 && !valid; i++) {//判断能否到达下一个
            //是否已经遍历过了
            int s = f + i;
            if(map.containsKey(s) && !map.get(s).contains(i)){
                map.get(s).add(k);
                dfs(s,e,i);
            }
        }
    }
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; ++i) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k > j + 1) {//从j->i 最多跳j + 1步
                    break;
                }
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] s = {0,1,3,5,6,8,12,17};
        boolean b = new Solution165().canCross(s);
        System.out.println(b);
    }
}
