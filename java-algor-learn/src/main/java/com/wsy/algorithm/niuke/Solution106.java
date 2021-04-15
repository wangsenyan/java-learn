package com.wsy.algorithm.niuke;


import java.util.*;

public class Solution106 {
    public String largestNumber(int[] nums) {
        List<String> str = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            str.add(String.valueOf(nums[i]));
        }
        Collections.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
               return o2.concat(o1).compareTo(o1.concat(o2));
            }
        });
        while (str.size() > 1 && str.get(0).equals("0"))
            str.remove(0);
        String res = String.join("", str);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,2,2342,42,34,546,567,6,87,9,79,79,90,8,8,0,5,7,363,9};
        String s = new Solution106().largestNumber(nums);

    }
}
