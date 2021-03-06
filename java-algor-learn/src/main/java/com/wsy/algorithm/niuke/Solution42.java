package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution42 {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //肯定是一颗树、森林,数的最深节点?
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        PriorityQueue<int[]> edges = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });
        int[] height = new int[m * n];
        Arrays.fill(height,1);//先排序再进行遍地最大
        //初始化边
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                for(int k = 0;k < 4;k++){
                    int nx = i + dirs[k][0];
                    int ny = j + dirs[k][1];
                    int cu = i * n + j;
                    int nt = nx * n + ny;
                    if( nx >= 0 && nx < m && ny >= 0 && ny < n ){
                        int sub = matrix[i][j] - matrix[nx][ny];
                        if(sub > 0) edges.add(new int[]{nt,cu,matrix[nx][ny]});//从[nx,ny]->[i,j]
                        if(sub < 0) edges.add(new int[]{cu,nt,matrix[i][j]}); //从[i,j]->[nx,ny]
                    }
                }
            }
        }
        while (!edges.isEmpty()){
            int[] edge = edges.poll();
            int u = edge[0];
            int v = edge[1];
            height[v] = Math.max(height[v],height[u]+1);
        }
        return Arrays.stream(height).max().getAsInt();
    }

    public int longestIncreasingPath1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] nodes = new int[m * n][3];
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i],1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nodes[i*n+j] = new int[]{i, j, matrix[i][j]};
            }
        }
        Arrays.sort(nodes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b){
                return a[2] - b[2];
            }
        });
        int max = 1;
        for (int[] node : nodes) {
            int i = node[0];
            int j = node[1];
            for (int k = 0; k < 4; k++) {
                int nx = i + dirs[k][0];
                int ny = j + dirs[k][1];
                if( nx >= 0 && nx < m && ny >= 0 && ny < n ){
                    int sub = matrix[i][j] - matrix[nx][ny];
                    if(sub > 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[nx][ny] + 1);
                        max = Math.max(dp[i][j],max);
                    }//从[nx,ny]->[i,j]
                    if(sub < 0) {
                        dp[nx][ny] = Math.max(dp[nx][ny], dp[i][j] + 1);
                        max = Math.max(dp[nx][ny],max);
                    }//从[i,j]->[nx,ny]
                }
            }

        }
        return max;
    }
    public static void main(String[] args) {
        int[][] matrix = {{3,4,5},{3,2,6},{2,2,1}};
        int[][] ints = {{0}, {1}, {5}, {5}};
        System.out.println(new Solution42().longestIncreasingPath1(matrix));

    }
}
