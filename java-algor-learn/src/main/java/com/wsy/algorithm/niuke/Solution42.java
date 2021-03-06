package com.wsy.algorithm.niuke;

import java.util.Arrays;

public class Solution42 {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[] leaf = new boolean[m * n];//是否是叶子节点
        Arrays.fill(leaf,true);
        int[] parent = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }
        //初始化边
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                for(int k = 0;k < 4;k++){
                    int nx = i + dirs[k][0];
                    int ny = j + dirs[k][1];
                    int cu = i * m + j;
                    int nt = nx * m + ny;
                    if( nx >= 0 && nx < m && ny >= 0 && ny < n ){
                        int sub = matrix[i][j] - matrix[nx][ny];
                        if(sub > 0) {
                              leaf[cu] = false;
                              parent[nt] = cu;
                        }
                        if(sub < 0) {
                            leaf[nt] = false;
                            parent[cu] = nt;
                        }
                    }
                }
            }
        }
        int max = 1;
        for (int i = 0; i < m * n; i++) {
            if(leaf[i]){
                int height = 1;
                int root = i;
                while (parent[root]!= root){
                    height++;
                    root = parent[root];
                }
                max = max < height ?height:max;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix={{7,7,5},{2,4,6},{8,2,0}};
        int i = new Solution42().longestIncreasingPath(matrix);
        System.out.println(i);

    }
}
