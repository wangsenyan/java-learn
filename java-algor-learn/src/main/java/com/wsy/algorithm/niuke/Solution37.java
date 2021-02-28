package com.wsy.algorithm.niuke;

public class Solution37 {

        public int minPathSum(int[][] grid) {
            //使用动态规划
            //dp[i][j]最短距离为dp[i][j] + min(dp[i][j-1],dp[i-1][j])
            int row = grid.length;
            int col = grid[0].length;
            int[] dp = new int[col];
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(i==0) dp[j]= j==0?grid[i][j]:dp[j-1]+grid[i][j];
                    else{
                        if(j==0 || dp[j]<dp[j-1])
                            dp[j]= grid[i][j]+dp[j];
                        else
                            dp[j]= grid[i][j]+dp[j-1];
                    }
                    System.out.println(dp[j]);
                }
            }
            return dp[col-1];
        }

    public static void main(String[] args) {
            int[][] arr = {{1,2,3},{4,5,6}};
        new Solution37().minPathSum(arr);
    }
}
