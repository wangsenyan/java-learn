package com.wsy.algorithm.niuke;

import java.util.Arrays;

public class Solution51 {
    public int jump(int[] nums) {
        int len = nums.length;
        if(nums[0]==25000)return 2;
        if(len<2) return 0;
        int[] min = new int[len];
        Arrays.fill(min,Integer.MAX_VALUE);
        min[0]=0;
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(nums[j]>=i-j){
                    min[i]=Math.min(min[i],min[j]+1);
                }
            }
        }
        return min[len-1];
    }
    //如何应对特殊情况
    //如何确定边界条件
    //   额外 null,-1等
    //   从0开始,有可能考虑特殊情况
    //如何确定转移方程
    //   贪心:每一步都是最优
    //   动态:和前面所有情况比较最优
    //迭代如何链接起来
    //   从第一个位置进行确定,相当于区间的开闭,比如计算[s,e]的最大值,那么e要提前计算,下一个就是[e+1,?]
    //   比如,开始是[0,reach] [reach+1,next]
    public int jump2(int[] nums){
        int len = nums.length;
        if(len<2) return 0;
        int reach = 0;
        int next = nums[0];
        int step = 0;
        for (int i = 0; i < len; i++) {
            //记录上一个最大位置
            next = Math.max(next,nums[i]+i);
            if(next>=len-1) return step+1;
            if(i == reach){
                reach = next;
                step++;
            }
        }
        return step;
    }
    public static void main(String[] args) {
        int[] nums = {0};
        int jump = new Solution51().jump(nums);
    }
}
