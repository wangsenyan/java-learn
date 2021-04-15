package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution76 {
    public int reversePairs(int[] nums) {
        List<Long> list = new ArrayList<>();
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans += binSearch(list, (long) nums[i]);
            list.add(binSearch(list, (long) nums[i] * 2), (long) nums[i] * 2);
        }
        return ans;
    }

    private int binSearch(List<Long> list, long target) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{-69,-221,-70,26,484,-173,138,-96,23,-7,39,193,27};
        int i1 = new Solution76().reversePairs(nums);
    }
}