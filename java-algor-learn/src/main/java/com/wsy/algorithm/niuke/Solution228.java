package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

class Solution228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        if(n == 0 ) return res;
        int l = 0;
        int r = 0;
        for(int i = 1; i < n ; i++){
            if(nums[r]+1!=nums[i]) {
                if(l==r)res.add(""+nums[l]);
                else res.add(nums[l] + "->" + nums[r]);
                l = i;
            }
            r=i;
        }
        if(l==r)res.add(""+nums[l]);
        else res.add(nums[l] + "->" + nums[r]);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0};
        List<String> strings = new Solution228().summaryRanges(nums);
        System.out.println(strings);
    }
}