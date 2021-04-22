package com.wsy.algorithm.niuke;

public class Solution125 {
    //暴力解法
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] sum = new int[m+1][n+1];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n; j++) {
                //sum[i][j] = sum[i][j-1] + sum[i-1][j] + matrix[i][j] - sum[i-1][j-1]
                sum[i][j] = matrix[i-1][j-1] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
                for (int l = 0; l < i; l++) {
                    for (int o = 0; o < j; o++) {
                        int sub = sum[i][j] - sum[i][o] - sum[l][j] + sum[l][o];
                        if(sub == k) return k;
                        if(sub < k) res = Math.max(res,sub);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] mtrix = {{1,0,1},{0,-2,3}} ;
        int k = 5;
        int i = new Solution125().maxSumSubmatrix(mtrix, k);
        System.out.println(i);
    }
}

