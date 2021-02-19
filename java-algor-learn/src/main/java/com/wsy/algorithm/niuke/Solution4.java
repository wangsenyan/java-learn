package com.wsy.algorithm.niuke;

public class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //找出每个数组一半以上的,对他们进行比较
        //从较大的向前，较小的向后
        int len1 = nums1.length;
        int len2 = nums2.length;
        int index = 0,i=0,j=0;
        int mid = (len1 + len2)/2;
        int req = (len1 + len2)%2;
        int mid0=0,mid1=0;
        while (index<mid+1){
            //if req==0 mid,mid+1
            //   req!=0 mid+1
            int cur ;
            if(i<len1 && j< len2){
                if(nums1[i]<=nums2[j]) {
                    cur = nums1[i];
                    i++;
                }
                else {
                    cur = nums2[j];
                    j++;
                }
            }else if(i<len1){
                cur = nums1[i];
                i++;
            }else {
                cur = nums2[j];
                j++;
            }
            if(index==mid-1) mid0=cur;
            if(index==mid) mid1=cur;
            index++;
        }
        System.out.println(index + " i="+i +" j="+j + " mid=" + mid +  " mid0=" + mid0 + " mid1=" + mid1);
        return req==0?(mid0 + mid1)/2.0:mid1;
    }

    public static void main(String[] args) {
        int num1[] = {1,2};
        int num2[] = {3,4};
        double medianSortedArrays = new Solution4().findMedianSortedArrays(num1, num2);
        System.out.println(medianSortedArrays);
    }
}
