package com.wsy.algorithm.niuke;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution752 {
    public int openLock(String[] deadends, String target) {
        //bfs,经过的不能再经过,否则循环
        //用整数表示
        Set<Integer> seen = new HashSet<>();
        Set<Integer> dead = new HashSet<>();
        for (String d : deadends) {
            dead.add(Integer.parseInt(d));
        }
        if(dead.contains(0)) return -1;
        if(target.equals("0000") && !dead.contains(0)) return 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(0);
        int tar = Integer.parseInt(target);
        int deep = 0;
        while (!stack.isEmpty()){
            //四个位数分别加减1
            deep++;
            int m = stack.size();
            for (int i = 0; i < m; i++) {
                int cur = stack.removeFirst();
                if(!seen.contains(cur)){
                    int rem = 1;
                    int num = cur;
                    for (int j = 0; j < 4; j++) {
                        int bit = num % 10;//(9->0,0->9)
                        int next1 = bit == 9 ? (cur - 9 * rem) : (cur + rem);
                        int next2 = bit == 0 ? (cur + 9 * rem) : (cur - rem);
                        rem*=10;
                        num/=10;
                        if(tar == next1 || tar == next2) {
                            return deep;
                        }
                        if(!dead.contains(next1) && !seen.contains(next1)){
                            stack.add(next1);
                        }
                        if(!dead.contains(next2)&&!seen.contains(next2)){
                           stack.add(next2);
                        }
                    }
                    seen.add(cur);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] dead = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        int i = new Solution752().openLock(dead, target);
        System.out.println(i);
    }
}
