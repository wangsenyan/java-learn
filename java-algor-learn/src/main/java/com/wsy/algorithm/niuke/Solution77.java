package com.wsy.algorithm.niuke;

public class Solution77 {
    private long[] sum;//保存0到i的大小
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        sum = new long[n];
        for(int i =0 ;i< n ;i++){
            sum[i] = (i==0 ? nums[0] : sum[i-1] + nums[i]);
        }
        return countRangeSumHelper(0,n-1,lower,upper);
        //解法一，O(n^2)
        //    int n = nums.length;
        //    long[] dp = new long[n];
        //    int total = 0;
        //    for(int i=0;i<n;i++){
        //        for(int j=i;j<n;j++){//[i,j]  = [i-1,j-1]
        //           if(i==0 && j ==0) dp[j] = nums[i];
        //           else if(i==0) dp[j] = dp[j-1] + nums[j];
        //           else
        //             dp[j] = dp[j] - dp[i-1];
        //           if(dp[j]>=lower && dp[j]<=upper)
        //             total++;
        //        }
        //    }
        //    return total;
        //解法2,归并法
        //每次返回求和的值[i,j]

    }
    private int countRangeSumHelper(int left,int right,int lower, int upper){
        if(left == right) return sum[left]>=lower && sum[left]<=upper?1:0;
        int mid = (left + right)/2;
        int lnum = countRangeSumHelper(left,mid,lower,upper);
        int rnum = countRangeSumHelper(mid+1,right,lower,upper);
        int ret = lnum + rnum;
        int i = left;
        int l = mid + 1;
        int r = mid + 1;
        while(i<=mid){
            while(l<=right && sum[l] - sum[i]<lower){
                l++;
            }
            while(r<=right && sum[r] - sum[i]<=upper){
                r++;
            }
            ret+= r - l;
            i++;
        }
        //合并
        long [] sorted = new long[right - left +1];
        int idx = left;
        int p1 = left,p2 = mid+1;
        while(p1<=mid || p2<=right){
            if(p1<=mid && p2<=right) sorted[idx++]=(sum[p1]<sum[p2]?sum[p1++]:sum[p2++]);
            else if(p1<=mid) sorted[idx++]=sum[p1++];
            else sorted[idx++]=sum[p2++];
        }
        for(int k = 0;k < right - left +1;k++){
           sum[left+k] = sorted[k];
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,5,-1,-3};
        int lower = -2;
        int upper = 2;
        int i = new Solution77().countRangeSum(nums, lower, upper);
    }
}
