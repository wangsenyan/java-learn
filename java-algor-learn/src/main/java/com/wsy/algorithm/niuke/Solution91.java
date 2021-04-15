package com.wsy.algorithm.niuke;

import java.util.HashMap;

public class Solution91 {
    public int maxPoints(int[][] points) {
        //两两差值相同比例相同???
        //n^2
        int m = points.length;
        double[][] dp = new double[m][m]; //dp[i][j] = (point[j][1] - point[i][1]) / (point[j][0] - point[i][0])

        //找出值相同的最大个数
        int max = 0;
        for(int i = 0;i < m ;i++){
            HashMap<Double,Integer> hash = new HashMap<>();
            for(int j = i+1;j < m ;j++){
                if(points[j][0] == points[i][0]){//等差数列,横坐标和纵坐标等差数列
                   dp[i][j] = Double.MAX_VALUE;
                }else if(points[j][1] == points[i][1]){
                    dp[i][j] = 0.0;
                }else{
                    dp[i][j] = (double) (points[j][1] - points[i][1]) / (double) (points[j][0] - points[i][0]);
                }
                Integer kv = hash.getOrDefault(dp[i][j], 0);
                hash.put(dp[i][j],kv +1 );
                max = Math.max(kv + 1,max);
            }
        }
        return  max +1;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{2,3},{3,3},{-5,3}};
        int i = new Solution91().maxPoints(points);
        System.out.println(i);

    }
}
