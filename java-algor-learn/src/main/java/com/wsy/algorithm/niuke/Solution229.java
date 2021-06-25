package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution229 {
    public List<Integer> majorityElement0(int[] nums) {
        Set<Integer> res = new HashSet<>();
        int n = nums.length;//范围查找nlgn
        int m = n / 3;

        if(n < 3){
            for (int num : nums) {
                res.add(num);
            }
            return new ArrayList<>(res);
        }
        Arrays.sort(nums);
        int k = 1;
        for (int i = 1; i < n; i++) {
          if(nums[i]==nums[i-1]){
              k++;
          }else{
              if(k > m) res.add(nums[i-1]);
              k = 1;
          }
        }
        if(k > m) res.add(nums[n-1]);
        return new ArrayList<>(res);
    }
    public List<Integer> majorityElement(int[] nums){
        // [1,1,1,1,2,2,2,2,3,3,3,3,4]
        // (n/3 + 1) * 3        40/3=13 14*
        List<Integer> r = new ArrayList<>();
        int m = nums.length/3;
        int x = 0,y = 0;
        int cx = 0, cy = 0;
        for (int num : nums) {
            if((num == x || cx == 0 ) && num != y){
                cx++;
                x=num;
            }else if(num == y || cy == 0){
                cy++;
                y=num;
            }else{
                cx--;
                cy--;
            }
        }
        cx = 0;
        cy = 0;
        for (int num : nums) {
            if(num==x) cx++;
            if(num==y) cy++;
        }
        if(cx > m) r.add(x);
        if(cy > m && x!=y) r.add(y);
        return r;
    }
    public static void main(String[] args) {
        int[] nums = {1,0};
        List<Integer> list = new Solution229().majorityElement(nums);
        System.out.println(list);
    }
}
