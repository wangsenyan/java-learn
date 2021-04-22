package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution130 {
    public int leastInterval(char[] tasks, int n) {
        int m = tasks.length;
        Map<Character,Integer> map = new HashMap<>();
        //n+1长度
        int maxExec = 0;
        for (char task : tasks) {
           int t0 =  map.getOrDefault(task,0) + 1;
            map.put(task,t0);
            maxExec = Math.max(t0,maxExec);
        }
        int maxCount = 0;
        for (Map.Entry<Character, Integer> set : map.entrySet()) {
            if(maxExec == set.getValue())
                maxCount++;
        }
        return Math.max((maxExec - 1) * (n + 1) + maxCount , m);
    }
}
