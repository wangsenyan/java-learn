package com.wsy.algorithm.niuke;

public class Solution50 {
    private int[]  nums1;
    private int[]  nums2;
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        int len2 = nums2.length;
        int len1 = nums1.length;
        return (findKthSortedArrays(0,0,(len1 + len2 + 1)/2) + findKthSortedArrays(0,0,(len1 + len2 + 2)/2))/2.0;//一定要除以2.0
    }
    private int findKthSortedArrays(int n1,int n2,int k){
        if(n1 >= nums1.length) return nums2[n2 + k-1];
        if(n2 >= nums2.length) return nums1[n1 + k-1];
        if(k==1) return Math.min(nums1[n1],nums2[n2]);
        int mid1 = (n1 + k/2 -1 < nums1.length) ? nums1[n1 + k/2 -1] : Integer.MAX_VALUE;
        int mid2 = (n2 + k/2 -1 < nums2.length) ? nums2[n2 + k/2 -1] : Integer.MAX_VALUE;
        if(mid1 < mid2){
            return findKthSortedArrays(n1+k/2,n2,k - k/2);
        }else{
            return findKthSortedArrays(n1,n2+k/2,k - k/2);//少了k/2,所以找第k-k/2
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
        double medianSortedArrays = new Solution50().findMedianSortedArrays(nums1, nums2);
    }
}
