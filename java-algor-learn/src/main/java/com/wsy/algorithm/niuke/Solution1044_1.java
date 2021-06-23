package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution1044_1 {
    private String s;
    public int search(int L, int a, long modulus, int n, int[] nums) {
        long h = 0;
        for (int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;
        //HashSet<Long> seen = new HashSet();
        HashMap<Long, List<Integer>> seen = new HashMap<>();
        seen.put(h, new ArrayList(){{add(0);}});
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;
        //哈希碰撞

        for (int start = 1; start < n - L + 1; ++start) {
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L - 1]) % modulus;
            if (seen.containsKey(h)) {
                List<Integer> sts = seen.get(h);
                for (int st : sts) {
                    if(s.substring(st,st+L).equals(s.substring(start,start + L))) return start;
                }
                sts.add(start);
                seen.put(h,sts);
            }else{
                ArrayList<Integer> sts = new ArrayList<>();
                sts.add(start);
                seen.put(h,sts);
            }

        }
        return -1;
    }

    public String longestDupSubstring(String S) {
        int n = S.length();
        this.s = S;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) nums[i] = (int)S.charAt(i) - (int)'a';
        int a = 26;
        long modulus = (long)Math.pow(2, 32);
        int left = 1, right = n;
        int L;
        while (left != right) {
            L = left + (right - left) / 2;
            if (search(L, a, modulus, n, nums) != -1) left = L + 1;
            else right = L;
        }

        int start = search(left - 1, a, modulus, n, nums);
        return start != -1 ? S.substring(start, start + left - 1) : "";
    }

    public static void main(String[] args) {
        String ss = "nnpxouomcofdjuujloanjimymadkuepightrfodmauhrsy";
        String s = new Solution1044_1().longestDupSubstring(ss);
        System.out.println(s);
    }
}