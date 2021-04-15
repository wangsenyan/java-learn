package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程表2 https://leetcode-cn.com/problems/course-schedule-ii/
 * 1. Solution102 dfs
 * 2. Solution103 bfs
 */
class Solution102 {
    private List<List<Integer>> edges;
    private int[] visited;
    private boolean sign = true;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //方法一：dfs 拓扑排序
        int n = prerequisites.length;
        edges = new ArrayList<List<Integer>>();

        for(int i = 0; i< numCourses ;i++){
            edges.add(new ArrayList<Integer>());
        }
        for(int i = 0;i < n;i++){
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        visited = new int[numCourses];
        //深度遍历,节点有3状态,0 未访问 1 访问中 2 已访问
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < numCourses && sign ;i++){
            if(visited[i]==0){//未访问
               arr.addAll(0,dfs(i));
            }
        }

        return sign ? arr.stream().mapToInt(Integer::intValue).toArray() : new int[]{};
        //方法二：bfs
    }
    private List<Integer> dfs(int u){
        visited[u] = 1;
        List<Integer> sub = new ArrayList<>();
        for(int v : edges.get(u)){
            if(visited[v]==0){
                List<Integer> child = dfs(v);
                sub.addAll(0,child);
            }else if(visited[v]==1){
                sign = false;
                return sub;
            }
        }
        visited[u] = 2;
        sub.add(0,u);
        return sub;
    }

    public static void main(String[] args) {
        int num = 4;
        int[][] p = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        int[] order = new Solution102().findOrder(num, p);
        System.out.println("");
    }
}