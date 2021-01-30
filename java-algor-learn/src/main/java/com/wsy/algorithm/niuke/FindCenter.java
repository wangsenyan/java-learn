package com.wsy.algorithm.niuke;

public class FindCenter {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int mid = (l1 + l2)/2;
        int i=0,j=0;
        while (true){
            if(nums1[i]<=nums2[j])
                i++;
            else
                j++;
        }
    }
}
