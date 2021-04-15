package com.wsy.algorithm.niuke;

public class Solution67 {
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            boolean[] row = new boolean[m];
            boolean[] col = new boolean[n];
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    row[i] |= matrix[i][j]==0;
                    col[j] |= matrix[i][j]==0;
                    //System.out.println(row[i] + " " + col[j]);
                }
            }
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    System.out.println(row[i] + " " + col[j]);
                    matrix[i][j]= (row[i] || col[j]) ? 0 : 1;
                }
            }
        }

    public static void main(String[] args) {
            int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        new Solution67().setZeroes(matrix);
        System.out.println("1");
    }
}
