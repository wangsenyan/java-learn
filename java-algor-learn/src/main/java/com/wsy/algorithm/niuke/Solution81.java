package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution81 {
    private List<List<Integer>> ans;
    private int[] nums;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //原来的思路,不允许重复的话,用2进制
        Arrays.sort(nums);
        ans = new ArrayList<>();
        this.nums = nums;
        //遇到重复的,只能是1个，2个，。。。
        List<Integer> sub = new ArrayList<>();
        subsetsWithDupHelper(0,true,new ArrayList<>());
        subsetsWithDupHelper(0,false,new ArrayList<>());
        return ans;
    }
    private void subsetsWithDupHelper(int index,boolean has,List<Integer> sub){
        if(index == nums.length) {
            ans.add(sub);
            return;
        }
        int cur = nums[index];
        int next = index + 1;
        while (next < nums.length && cur == nums[next]){
            next++;
        }
        if(next > index + 1) {//有重复
            if(!has) {//无,全无
                if(next == nums.length) {
                    ans.add(sub);
                }else{
                    List<Integer> arr = copyOf(sub);
                    subsetsWithDupHelper(next,true,sub);
                    subsetsWithDupHelper(next,false,arr);
                }
            }else{
                if(next == nums.length) {
                    for (int i = index; i < next; i++) {
                        sub.add(cur);
                        List<Integer> arr = copyOf(sub);
                        ans.add(arr);
                    }
                }else{
                    for (int i = index; i < next; i++) {
                        sub.add(cur);
                        List<Integer> arr = copyOf(sub);
                        List<Integer> arr2 = copyOf(sub);
                        subsetsWithDupHelper(next,true,arr);
                        subsetsWithDupHelper(next,false,arr2);
                    }
                }
            }
        }else{
            if(has) sub.add(cur);
            if(next==nums.length){
                ans.add(sub);
            }else{
                List<Integer> arr = copyOf(sub);
                subsetsWithDupHelper(next,true,sub);
                subsetsWithDupHelper(next,false,arr);
            }
        }
    }

    private List<Integer> copyOf(List<Integer> source){
        ArrayList<Integer> res = new ArrayList<>();
        for (Integer it : source) {
            res.add(it);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{2,2,3,3};
        System.out.println(new Solution81().subsetsWithDup(nums));
    }
}
