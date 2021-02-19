package com.wsy.algorithm.niuke;

import java.util.LinkedList;
import java.util.List;

public class Solution12 {
    public List<String> removeInvalidParentheses(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int len = s.length();
        int left = 0;
        int right = 0;
        for (int i = 0; i < len; i++) {
            if(s.charAt(i)=='(')
                left++;
            else if(s.charAt(i)==')')
                right++;
        }
        //依次删除,判断是否合理,删除个数>= abs(left-right)
        List<String> res = new LinkedList<>();
        return res;
    }
}
