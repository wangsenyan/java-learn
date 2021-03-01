package com.wsy.algorithm.niuke;

import java.util.ArrayList;

public class Solution42 {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //肯定是一颗树、森林,数的最深节点?
        ArrayList<int[]> edges = new ArrayList<int[]>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[] seen = new boolean[m*n];
        seen[0]=true;
        //初始化边
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                for(int k = 0;k < 4;k++){
                    int nx = i + dirs[k][0];
                    int ny = j + dirs[k][1];
                    int cu = i * m + j;
                    int nt = nx * m + ny;
                    if( nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nt] ){
                        int sub = matrix[i][j] - matrix[nx][ny];
                        if(sub > 0) edges.add(new int[]{cu,nt,sub});
                        if(sub < 0) edges.add(new int[]{nt,cu,-sub});
                        seen[nt] = true;
                    }
                }
            }
        }
        return 0;
    }
}
