package com.wsy.algorithm.niuke;
//Solution63
class Solution62 {
    private int[] prices;
    private int[] nleft;//记录前几次的left
    private int[] nright;
    public int maxProfit(int[] prices) {
        this.prices = prices;
        int n = prices.length;
        nleft = new int[n];
        nright = new int[n];
        int max = 0;
        maxProfitLeft(n);
        maxProfitRight(n);
        for(int i=0;i<n;i++){
            if(i==n-1) max = Math.max(max,nleft[i]);
            else max = Math.max(max,nleft[i]+nright[i+1]);
        }
        return max;
    }
    private void maxProfitLeft(int n){
        int left = 0;
        for(int i=left+1;i<n;i++){
            int sub = prices[i] - prices[left];
            if(sub<=0){
                left=i;
                nleft[i] =nleft[i-1];
            }else
                nleft[i]= nleft[i-1] < sub ? sub : nleft[i-1];
        }
    }
    private void maxProfitRight(int n){
        int right = n-1;
        for(int i=right-1;i>=0;i--){
            int sub = prices[right] - prices[i] ;
            if(sub<=0){
                right=i;
                nright[i] = nright[i+1];
            }else
                nright[i]= nright[i+1] < sub ? sub : nright[i+1];
        }
    }

    public static void main(String[] args) {
        int[] stock = new int[]{2,1,2,1,0,0,1};
        int i = new Solution62().maxProfit(stock);

    }
}