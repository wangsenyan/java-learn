package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.OptionalInt;

public class Solution16 {
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if(len<2) return len;
        Arrays.sort(nums);
        int max = 1;
        int cur = 1;
        for (int i = 1; i < len; i++) {
            if(nums[i-1] + 1 == nums[i]){
                cur++;
            }else if(nums[i-1] == nums[i]){
                continue;
            }else {
                cur=1;
            }
            max = max > cur ? max : cur;
        }
        return max;
    }
    public int longestConsecutiveOn(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if(numSet.remove(cur)){
                int curLen = 1;
                while (numSet.remove(--cur)) curLen++;
                cur = nums[i];
                while (numSet.remove(++cur)) curLen++;
                max = max > curLen ? max : curLen;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] ints = {1,2,0,1};
        System.out.println(new Solution16().longestConsecutive(ints));
    }
}
