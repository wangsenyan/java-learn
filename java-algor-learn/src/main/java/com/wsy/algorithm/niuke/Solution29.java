package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution29 {
    private int[][] graph;
    private int[] clean;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        this.graph = graph;
        int N = graph.length;
        clean = new int[N];
        Arrays.fill(clean, 1);
        for (int x: initial)
            clean[x] = 0;//被感染的节点
        ArrayList<Integer>[] infectedBy = new ArrayList[N];
        for (int i = 0; i < N; ++i)
            infectedBy[i] = new ArrayList();

        for (int u: initial) {
            Set<Integer> seen = new HashSet();
            dfs(u, seen);//深度遍历,找到被u感染的集合
            for (int v: seen)
                infectedBy[v].add(u);
        }

        // For each node u in initial, for every v not in initial
        // that is uniquely infected by u, add 1 to the contribution for u.
        int[] contribution = new int[N];
        for (int v = 0; v < N; ++v)
            if (infectedBy[v].size() == 1)
                contribution[infectedBy[v].get(0)]++;

        // Take the best answer.
        // Arrays.sort(initial);
        int ans = initial[0], ansSize = -1;
        for (int u: initial) {
            int score = contribution[u];
            if (score > ansSize || score == ansSize && u < ans) {
                ans = u;
                ansSize = score;
            }
        }
        return ans;
    }
    public void dfs(int u, Set<Integer> seen) {
        for (int v = 0; v < graph.length; ++v)
            if (graph[u][v] == 1 && clean[v] == 1 && !seen.contains(v)) {
                seen.add(v);
                dfs(v, seen);
            }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 0, 0},{1, 1, 1, 0},{0, 1, 1, 1},{0, 0, 1, 1}};
        int[] initial = {0,1};
        int i = new Solution29().minMalwareSpread(matrix,initial);
    }
}
