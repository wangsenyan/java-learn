package com.wsy.algorithm.niuke;






import java.util.*;

public class Solution100 {
    private Map<Integer, ArrayList<Integer>> edges;
    private boolean[] seen;
    //超出时间限制
    //判断是否有环
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        //是否有环?是否都在一起

        edges = new HashMap<>();
        int n = prerequisites.length;
        seen = new boolean[numCourses];
        for(int i = 0 ; i < n; i++){
            int[] p = prerequisites[i];
            ArrayList<Integer> edge = edges.getOrDefault(p[1], new ArrayList<>());

            edge.add(p[0]);
            edges.put(p[1],edge);
        }

        //dfs
        //判断无环
        boolean ans = true;
        for (int i = 0; i < numCourses; i++) {
            if(ans){
                ans &= dfs(i);
            }
        }
        return ans;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //是否有环?是否都在一起
        int n = prerequisites.length;
        boolean[][] matrix = new boolean[numCourses][numCourses];
        for (int i = 0; i < n; i++) {
            int[] p = prerequisites[i];
            if(matrix[p[0]][p[1]] ||  p[0] == p[1]) return false;
            matrix[p[1]][p[0]] = true;
        }
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < numCourses; j++) {
                for (int k = 0; k < numCourses; k++) {
                    if(i!=j && j!=k && k!=i && matrix[i][k] && matrix[k][j]){
                        if(matrix[j][i]) return false;
                        else matrix[i][j] = true;
                    }
                }
            }
        }
        return true;
    }
    public boolean dfs(int u){
        ArrayList<Integer> V = edges.get(u);
        //V==null,没前置条件,那么不为true,叶子为false
        boolean r = true;
        if(V!=null){
            seen[u] = true;
            for (Integer v : V) {
                if(seen[v]) return false;
                r &= dfs(v);
            }
            seen[u] = false;
        }
        return r;

    }

    public static void main(String[] args) {
        int[][] p = new int[][]{{1,4},{2,4},{3,1},{3,2}};
        int num = 5;
        boolean b = new Solution100().canFinish(num, p);
    }
}
