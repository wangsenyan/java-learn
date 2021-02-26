package com.wsy.algorithm.niuke;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution17 {
    public int find(int[] nums,int limit){
        int len = nums.length;
        int st=0;
        TreeMap<Integer,Integer> tree = new TreeMap<>();
        int max=0;
        for (int i = 0; i < len; i++) {
            int lst = st;
            tree.put(nums[i],i);
            Map.Entry<Integer, Integer> mx = tree.lastEntry();
            Map.Entry<Integer, Integer> mi = tree.firstEntry();
            if(mx.getKey() - mi.getKey() > limit){
                st = Math.min(mx.getValue(),mi.getValue())+1;
                for (int j = lst; j < st; j++) {
                    tree.remove(nums[j]);
                }
            }else {
                max = max > (i-st+1)?max:(i-st+1);
            }
        }
        return max;
    }
    public int longestSubarray(int[] nums, int limit) {

        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < nums.length && left < nums.length) {
            minQueue.add(nums[right]);
            maxQueue.add(nums[right]);

            if (maxQueue.peek() - minQueue.peek() <= limit) {
                ans = Math.max(ans, right - left + 1);
                right++;
                continue;
            }

            maxQueue.remove((Integer) nums[left]);
            minQueue.remove((Integer) nums[left]);
            left++;
            right++;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {8,2,4,7};
        int limit = 4;
        System.out.println(new Solution17().find(arr,limit));
    }
}
