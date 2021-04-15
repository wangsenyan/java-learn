package com.wsy.algorithm.niuke;

import java.util.Arrays;

/**
 * 股票买卖,力扣123题
 * 状态转移,在位置i
 * 记录每次位置,是否有股票,最大次数
 *    dp[n][k][t] 天数,次数,持有
 *    dp[i][k][0] //没持有
 *    dp[i][k][1] //持有
 * 1. 有股票在手
 *    -> 卖  dp[i] =  prices[i] + dp[i-1];
 *    -> 不卖 dp[i] = dp[i-1]
 * 2. 没股票在手
 *    -> 买 dp[i] = -prices[i] + dp[i-1]
 *    -> 不买 dp[i] = dp[i-1]
 * 3. 停止转移
 *    -> 最后一天
 *    -> 股票卖了,到达最大买卖次数
 */
class Solution63 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int maxK = k;
        int[][][] dp = new int[n][maxK+1][2];
        for (int t = 1; t <= maxK; t++) {
            dp[0][t][1] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= maxK; t++) {
                dp[i][t][0] = Math.max(dp[i-1][t][0], dp[i-1][t][1]+prices[i]);
                dp[i][t][1] = Math.max(dp[i-1][t][1], dp[i-1][t-1][0]-prices[i]);
            }
        }
        return dp[n-1][maxK][0];
    }

    /**
     * 动态规划
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit1(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int[][] buy = new int[n][k+1];//n代表天数,k代表卖出次数
        int[][] sell = new int[n][k+1];
        Arrays.fill(buy[0],Integer.MIN_VALUE/2);//防止溢出
        Arrays.fill(sell[0],Integer.MIN_VALUE/2);
        buy[0][0] = -prices[0];//第1天只能买入
        sell[0][0] = 0;//第1天不能卖出
        for (int i = 1; i < n; i++) { //只能第1次买,第1次卖,第2次买,第2次卖......
            buy[i][0] = Math.max(buy[i-1][0],sell[i-1][0]-prices[i]);//第0次交易
            for (int j = 1; j <=k; j++) {
                buy[i][j] = Math.max(buy[i-1][j],sell[i-1][j]-prices[i]);
                sell[i][j]= Math.max(sell[i-1][j],buy[i-1][j-1] + prices[i]);
            }
        }
        return Arrays.stream(sell[n-1]).max().getAsInt();
    }

    /**
     * 简化
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int[] buy = new int[k+1];//n代表天数
        int[] sell = new int[k+1];
        Arrays.fill(buy,Integer.MIN_VALUE/2);
        Arrays.fill(sell,Integer.MIN_VALUE/2);
        buy[0] = -prices[0];//第1天只能买入
        sell[0] = 0;//第1天不能卖出

        for (int i = 1; i < n; i++) { //只能第1次买,第1次卖,第2次买,第2次卖......
            buy[0] = Math.max(buy[0],sell[0]-prices[i]);
            for (int j = 1; j <=k; j++) {
                buy[j] = Math.max(buy[j],sell[j]-prices[i]);
                sell[j]= Math.max(sell[j],buy[j-1] + prices[i]);
            }
        }
        return Arrays.stream(sell).max().getAsInt();
    }
    public static void main(String[] args) {
        int t = 1;
        int[] stock = new int[]{1};
        int i = new Solution63().maxProfit(t, stock);
    }
}