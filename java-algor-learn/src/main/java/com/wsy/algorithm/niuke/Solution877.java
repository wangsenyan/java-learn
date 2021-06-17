package com.wsy.algorithm.niuke;

/**
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 *
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 *
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 *
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stone-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution877 {

    public boolean stoneGame(int[] piles) {
       //每步都走最好的,那么倒数第二步呢?
        //递归???递归???
        int n = piles.length;
        //当前步数和前一个人有关,每步拿最大的吗?
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int k = i + 1; k < n ; k++) {
                //由内向外dp[i+1][k]  dp[i][k] dp[i][k-1]
                dp[i][k] = Math.max(piles[i] - dp[i+1][k],piles[k] - dp[i][k-1]);
            }
        }
        return dp[0][n-1] > 0;
    }

    public static void main(String[] args) {
        int[] p = { 1,2,3,4,5,6};
        boolean b = new Solution877().stoneGame(p);
        System.out.println(b);
    }
}
