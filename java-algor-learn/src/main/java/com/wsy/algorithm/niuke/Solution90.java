package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution90 {
    public int numRabbits(int[] answers) {
        //找到相同的数字,向上求整
        int n = answers.length;
        HashMap<Integer,Integer> rabbit = new HashMap<>();
        for(int i = 0;i< n ; i++){
            Integer c = rabbit.getOrDefault(answers[i], 0);
            rabbit.put(answers[i],c+1);
        }
        AtomicInteger nums = new AtomicInteger();
        rabbit.forEach((k,v)->{
            nums.addAndGet((v + k) / (k + 1) * (k + 1));
        });
        return nums.get();
    }

    public static void main(String[] args) {
        int[] answers = new int[]{1,1,2};
        System.out.println(new Solution90().numRabbits(answers));
    }
}
