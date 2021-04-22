package com.wsy.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

public class Solution132 {
    private String s1;
    private String s2;
    private Map<int[],Boolean> map;
    public boolean isScramble(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.map = new HashMap<>();
        int n = s1.length();
        if(n==1) return s1.equals(s2);

        //随机从位置i出拆分
        //子序列是先拆分再合并
        return dfs(0,n,0,n);
    }
    //超时
    public boolean dfs(int st1,int ed1,int st2,int ed2){
        String ss1 = s1.substring(st1, ed1);
        String ss2 = s2.substring(st2, ed2);
        if(ss1.equals(ss2)) {
            return true;
        }
        if(st1 == ed1) return false;
        boolean tag = false;
        for (int i = 1; i < ed1 - st1; i++) {
            tag =  ((dfs(st1,st1 + i,st2,st2 + i)
                    && dfs(st1 + i ,ed1,st2 + i ,ed2))
                    || (dfs(st1,st1 + i,ed2 -i,ed2)
                    && dfs(st1 + i ,ed1 , st2, ed2 - i)));
            if(tag) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String a = "abcde";
        String b = "caebd";
        boolean scramble = new Solution132().isScramble(a, b);
    }
}
