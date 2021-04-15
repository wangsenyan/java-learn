package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution39 {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int row = cost.size();
        int col = cost.get(0).size();
        int[][] dp = new int[row][col];
        int sum = 0;
        for (int i = 0; i < row; i++) {
            //找到最小值
            ArrayList<Integer> idx = new ArrayList<>();
            idx.add(0);
            int min = cost.get(i).get(0);
            for (int j = 1; j < col; j++) {
                Integer cur = cost.get(i).get(j);
                if(cur==min){
                    idx.add(j);
                }else if(cur<min){
                    min = cur;
                    idx.clear();
                    idx.add(j);
                }
            }
            int sz = idx.size();
            for (int j = 0; j < sz; j++) {
                Integer col_idx = idx.get(j);//col的索引
                if(j==sz-1 || dp[i][col_idx]==0){
                    if(dp[i][col_idx]==0) {
                        sum += min;
                    }
                    dp[i][col_idx]=1;
                    break;
                }

            }
        }
        //对另一边进行同样操作
        for (int i = 0; i < col; i++) {
            ArrayList<Integer> idx = new ArrayList<>();

            int min = cost.get(0).get(i);
            idx.add(0);
            boolean tag = dp[0][i]==1? false:true;
            for (int j = 0; j < row; j++) {
                if(dp[j][i]==1) tag = false;
                    Integer cur = cost.get(j).get(i);
                    if(cur==min){
                        idx.add(j);
                    }else if(cur<min){
                        min=cur;
                        idx.clear();
                        idx.add(j);
                    }
                //优先考虑已连接
            }
            if(tag){
                int sz = idx.size();
                for (int j = 0; j < sz; j++) {
                    Integer col_idx = idx.get(j);//row的索引
                    if(j==sz-1 || dp[i][col_idx]==1){
                        if(dp[i][col_idx]==0){
                            sum+=min;
                        }
                        dp[i][col_idx]=1;
                        break;
                    }
                }
            }

        }
        return sum;
    }


    public int connectTwoGroups1(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        int[][] costMatrix = new int[m][1 << n];
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < (1 << n); i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0)
                        sum += cost.get(k).get(j);
                }
                costMatrix[k][i] = sum;
            }
        }
        int[][] dp = new int[m][1 << n];
        for (int i = 1; i < m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0] = costMatrix[0];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < (1 << n); j++)
                for (int k = 1; k < (1 << n); k++)
                    dp[i][j | k] = Math.min(dp[i][j | k], dp[i - 1][k] + costMatrix[i][j]);
        return dp[m - 1][(1 << n) - 1];
    }

    public static void main(String[] args) {
        int[][] c = {{93,56,92},{53,44,18},{86,44,69},{54,60,30}};
        List<List<Integer>> cost = new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < c[0].length; j++) {
                arr.add(c[i][j]);
            }
            cost.add(arr);
        }
        int i = new Solution39().connectTwoGroups1(cost);
        System.out.println(i);
    }
}
