package com.wsy.algorithm.niuke;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Solution32 {
    private HashSet<String> res = new HashSet<>();
    //private List<String> res = new LinkedList<>();
    public List<String> removeInvalidParentheses(String s) {
        char[] par = {'(',')'};
        remove(s, par,0 ,0);
        return res.stream().collect(Collectors.toList());
    }
    public void remove(String s,char[] par,int m,int n){
        int stack = 0;
        for (int i = m; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == par[0]) stack++;
            if(c == par[1]) stack--;
            if(stack>=0) continue;
            //“右”括号多出了,删除一个右括号
            for (int j = 0; j <=i ; j++) {
                char cc = s.charAt(j);
                if(cc == par[1] && (j==n || s.charAt(j-1)!=par[1])){
                    String ss = s.substring(0,j) + s.substring(j+1);
                    remove(ss,par,i,j);
                }
            }
            return;
        }
        s = new StringBuffer(s).reverse().toString();
        if(par[0] == '(')
            remove(s, new char[]{par[1],par[0]}, 0,0);
        else
            res.add(s);
    }

    public static void main(String[] args) {
        String s = "(a)())()";
        List<String> strings = new Solution32().removeInvalidParentheses(s);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
