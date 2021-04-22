package com.wsy.algorithm.niuke;

public class Solution88 {
    public int trap(int[] height) {
       int ans = 0;
       int n = height.length;
       int left = 0,right = n -1;
       int lmax = height[left];
       int rmax = height[right];
       while(left<right){
           if(height[left]<height[right]){
               left++;
               if(height[left]<lmax){
                   ans+=lmax - height[left];
               }else{
                   lmax=height[left];
               }
           }else{
               right--;
               if(height[right]<rmax)
                   ans+=rmax - height[right];
               else{
                   rmax = height[right];
               }
           }
       }
       return ans;
    }
}
