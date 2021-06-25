package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution241 {
    private final Set<Character> sign = new HashSet(){{add('+');add('-');add('*');}};
    public List<Integer> diffWaysToCompute(String expression) {
        //遇到符号,消与不消???
        //分别从不同的地方切开,然后进行操作
        List<Integer> res = new ArrayList<>();
        int n = expression.length();
        boolean tag = false;
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if(sign.contains(c)){
                tag = true;
                List<Integer> left = diffWaysToCompute(expression.substring(0,i));
                List<Integer> right = diffWaysToCompute(expression.substring(i+1));
                for (int  l : left) {
                    for (int r : right) {
                        switch (c){
                            case '+': res.add(l+r);break;
                            case '-': res.add(l-r);break;
                            case '*': res.add(l*r);break;
                        }
                    }
                }
            }
        }
        if(!tag) res.add(Integer.parseInt(expression));
        return res;
    }

    public static void main(String[] args) {
        String ex = "2-1-1";
        List<Integer> list = new Solution241().diffWaysToCompute(ex);
        System.out.println(list);
    }
}
