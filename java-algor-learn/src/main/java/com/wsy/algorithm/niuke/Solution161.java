package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

public class Solution161 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;

        // key：区间 [0..i] 里所有元素的和 % k
        // value：下标 i
        Map<Integer, Integer> map = new HashMap<>();
        // 从0到i的和
        map.put(0, -1);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (k != 0) {
                //如果求模运算相等,那么必定相差为k的倍数
                sum = sum % k;
            }

            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2,4,3};
        int k = 6;
        boolean b = new Solution161().checkSubarraySum(nums, k);
        System.out.println(b);
    }
}
