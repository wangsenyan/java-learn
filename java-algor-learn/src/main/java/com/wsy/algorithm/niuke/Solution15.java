package com.wsy.algorithm.niuke;

import java.util.Arrays;

public class Solution15 {
    public void nextPermutation(int[] nums) {
        //从尾部更改
        //交换
        int len = nums.length;
        for (int i = len -1; i >=0 ; i--) {
            for (int j = i-1; j >=0 ; j--) {
                if(nums[i] > nums[j]){
                    int tmp = nums[i];
                    for (int k = i; k >j; k--) {
                        nums[k] = nums[k-1];
                    }
                    nums[j] = tmp;
                    return;
                }
            }
        }
        int i = 0;
        int j = len -1;
        while (i<j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] ints = {2,3,1};
        new Solution15().nextPermutation(ints);
    }
}
