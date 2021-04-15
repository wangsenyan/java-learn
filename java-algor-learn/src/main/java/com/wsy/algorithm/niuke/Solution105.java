package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

public class Solution105 {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        //方法一: 矩阵
        boolean[][] matrix = new boolean[n][n];
        for (int[] p : prerequisites) {
            matrix[p[1]][p[0]] = true;
        }
        for (int i = 0; i < n; i++) {
            matrix[i][i] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(matrix[i][k] && matrix[k][j]){
                        matrix[i][j] = true;
                    }
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            ans.add(matrix[q[0]][q[1]]);
        }
        return ans;
        //方法二: 邻接矩阵
    }

    public static void main(String[] args) {
        int n = 13;
        int[][] p = new int[][]{{2,1},{2,7},{2,0},{2,10},{2,11},{1,7},{1,0},{1,9},{1,4},{1,11},{7,3},{7,9},{7,4},{7,11},{7,8},{3,6},{3,12},{3,5},{6,10},{6,8},{0,4},{12,9},{12,8},{9,4},{9,11},{9,8},{9,5},{10,8},{4,8}};
        int[][] q = new int[][]{{12,11},{11,1},{10,12},{9,10},{10,11},{11,12},{2,7},{6,8},{3,2},{9,5},{8,7},{1,4},{3,12},{9,6},{4,3},{11,4},{5,7},{3,9},{3,1},{8,12},{5,12},{0,8},{10,5},{10,11},{12,11},{12,9},{5,4},{11,5},{12,10},{11,0},{6,10},{11,7},{8,10},{2,1},{3,4},{8,7},{11,6},{9,11},{1,4},{10,8},{7,1},{8,7},{9,7},{5,1},{8,10},{11,8},{8,12},{9,12},{12,11},{6,12},{12,11},{6,10},{9,12},{8,10},{8,11},{8,5},{7,9},{12,11},{11,12},{8,0},{12,11},{7,0},{8,7},{5,11},{11,8},{1,9},{4,10},{11,6},{10,12}};
        List<Boolean> booleans = new Solution105().checkIfPrerequisite(n, p, q);
        System.out.println(booleans);
    }
}
