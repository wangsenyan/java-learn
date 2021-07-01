package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution310 {
    public List<Integer> findMinHeightTrees0(int n, int[][] edges) {
        //求树的高度
        int[][] hp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(hp[i],Integer.MAX_VALUE/3);
        }
        for (int[] edge : edges) {
            hp[edge[0]][edge[1]] = 1;
            hp[edge[1]][edge[0]] = 1;
        }
        for (int k = 0; k < n; k++) {
            hp[k][k] = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    hp[i][j] = Math.min(hp[i][k] + hp[k][j], hp[i][j]);
                }
            }
        }

        int[] height = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(j==i) continue;
                height[i] = Math.max(hp[i][j],height[i]);
            }
            min = Math.min(min,height[i]);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(height[i] == min) res.add(i);
        }
        return  res;
    }

    private Map<Integer,List<Integer>> graph;
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        //dfs
        //init
        this.graph = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> u = graph.getOrDefault(edge[0], new ArrayList<>());
            u.add(edge[1]);
            graph.put(edge[0],u);
            List<Integer> v = graph.getOrDefault(edge[1], new ArrayList<>());
            v.add(edge[0]);
            graph.put(edge[1],v);
        }
        //int[] h = new int[n];
        int m = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            int h = findHeight(i,seen);
            if( h < m){
                res.clear();
                res.add(i);
                m = h;
            }else if(h==m){
                res.add(i);
            }
        }
        return res;
    }
    //0,1,2
    private int findHeight(int m,Set<Integer> seen){
        //寻找m为root的树高
        List<Integer> ch = graph.get(m);
        seen.add(m);
        if(ch==null || ch.isEmpty()) return 0;
        int h = -1;
        for (int c : ch) {
            if(!seen.contains(c)){
                h = Math.max(h,findHeight(c,seen));
            }
        }
        return h == -1 ? 0 : h + 1;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        /*如果只有一个节点，那么他就是最小高度树*/
        if (n == 1) {
            res.add(0);
            return res;
        }
        /*建立各个节点的出度表*/
        int[] degree = new int[n];
        /*建立图关系，在每个节点的list中存储相连节点*/
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;/*出度++*/
            map.get(edge[0]).add(edge[1]);/*添加相邻节点*/
            map.get(edge[1]).add(edge[0]);
        }
        /*建立队列*/
        Queue<Integer> queue = new LinkedList<>();
        /*把所有出度为1的节点，也就是叶子节点入队*/
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            res.clear();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);
                List<Integer> neighbors = map.get(cur);
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res;/*返回最后一次保存的list*/
    }
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        List<Integer> minHeightTrees = new Solution310().findMinHeightTrees(n, edges);
        System.out.println(minHeightTrees);
    }
}
