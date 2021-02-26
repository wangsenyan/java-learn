package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution34 {
    private int[][] graph;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int N = graph.length;
        this.graph = graph;
        int[] clean = new int[N];
        Arrays.fill(clean,1);
        for(int x:initial)
            clean[x]=0;
        ArrayList<Integer>[] infectBy = new ArrayList[N];
        for (int i = 0; i < N; ++i)
            infectBy[i] = new ArrayList();
        for(int u:initial){
            clean[u]=1;
            Set<Integer> seen = new HashSet();
            dfs(u,seen,clean);
            for(int v : seen){
                infectBy[v].add(u);
            }
            clean[u]=0;
        }

        int[] contribution = new int[N];
        for(int i=0;i<N;++i){
            if(infectBy[i].size()==1)
                contribution[infectBy[i].get(0)]++;
        }

        int ans=initial[0],ans_size=-1;
        for(int u : initial){
            int score = contribution[u];
            if(score > ans_size || ans_size == score && u < ans){
                ans = u;
                ans_size = score;
            }
        }
        return ans;
    }
    private void dfs(int u,Set<Integer> seen,int[] clean){
        for(int v = 0;v<graph.length;++v){
            if(graph[u][v]==1 && !seen.contains(v)){
                seen.add(v);
                dfs(v,seen,clean);
            }
        }
    }

    public static void main(String[] args) {
        int[][]graph = {{1,1,1},{1,1,1},{1,1,1}};
        int[] initial = {0,1,2};
        new Solution34().minMalwareSpread(graph,initial);
    }
}
