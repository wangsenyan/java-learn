package com.wsy.algorithm.niuke;

import java.util.HashSet;

public class Solution36 {
    public int longestConsecutive(int[] nums) {
        //使用set保证
        //持续从左或右删除
        HashSet<Integer> set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        int max=0;
        for (int num : nums) {
            if(set.remove(num)){
                int sz = 1, cr = num;
                while (set.remove(--cr)) sz++;
                cr = num;
                while (set.remove(++cr)) sz++;
                max = max < sz ? sz: max;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arg ={0,3,7,2,5,8,4,6,0,1};
        int i = new Solution36().longestConsecutive(arg);
    }
}
