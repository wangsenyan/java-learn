package com.wsy.algorithm.niuke;

import java.util.*;

public class SolutionO38 {
    Set<String> set;
    boolean[] seen;
    char[] chars;
    public String[] permutation(String s) {
        //s的排列,如果有重复元素,跳过
        //不同原书的排列和相同元素的组合???
        int n = s.length();
        this.set = new HashSet<>();
        this.chars = s.toCharArray();
        this.seen = new boolean[n];
        LinkedList<Character> cs = new LinkedList<>();
        for (char c : s.toCharArray()) {
            cs.add(c);
        }
        dfs(0,s.length(),"",seen);
        return  this.set.toArray(new String[0]);
    }
    public void dfs(int i,int n, String s, boolean[] seen){
        if(i==n) {
            set.add(s);
            return;
        }
        for (int j = 0; j < n; j++) {
            if(!seen[j]){
                seen[j] = true;
                dfs(i+1,n,s+chars[j],seen);
                seen[j] = false;
            }
        }
    }

    public static void main(String[] args) {
        String s = "abc";
        String[] permutation = new SolutionO38().permutation(s);
        System.out.println(permutation);
    }
}
