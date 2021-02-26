package com.wsy.algorithm.niuke;

public class Solution23 {
    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col/2;j++){
                int tmp = A[i][j];
                A[i][j] = A[i][col-j-1];
                A[i][col-j-1]=tmp;
                System.out.println(A[i][j]);
            }
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                A[i][j]^=1;
            }
        }
        return A;
    }

}
