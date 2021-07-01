package com.wsy.algorithm.niuke;

import java.util.*;

public class LCP07 {
    //bfs方法
    public int numWays_bfs(int n, int[][] relation, int k) {
        //bfs
        //构建图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i,new ArrayList<>());
        }
        for (int[] edge : relation) {
            graph.get(edge[0]).add(edge[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int dp = 1;
        int rs = 0;
        while (!queue.isEmpty() && dp <= k){
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int u = queue.poll();
                List<Integer> vL = graph.get(u);
                for (int v : vL) {
                    if(dp==k && v == n-1) rs++;
                    queue.add(v);
                }
            }
            dp++;
        }
        return rs;
    }

    //dp
    public int numWays(int n, int[][] relation, int k){
        int[][] dp = new int[k+1][n];
        //只走k步
        dp[0][0] = 1;

        for (int i = 1; i <= k; i++) {
            for (int[] edge : relation) {
                dp[i][edge[1]]+=dp[i-1][edge[0]];
            }
        }
        return  dp[k][n-1];
    }
    public static void main(String[] args) {
        LCP07 lcp07 = new LCP07();
        int n = 5;
        int[][] relation = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        int k = 3;
        int i = lcp07.numWays(n, relation, k);
        System.out.println(i);
    }
}
