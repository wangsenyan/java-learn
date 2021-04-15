package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution108 {
    public String decodeString(String s) {
        //栈
        //StringBuilder stack = new StringBuilder();
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for(int i = 0; i < n;i++){
            char c = s.charAt(i);
            if(c==']'){
                StringBuilder sb = new StringBuilder();
                //while(!stack.isEmpty() && stack.getLast()!='['){
                while(stack.getLast()!='['){
                    sb.insert(0,stack.removeLast());
                }
                stack.removeLast();//删除[
                int m = 0;
                int d = 1;
                while (!stack.isEmpty() && Character.isDigit(stack.getLast())){
                    m = (stack.removeLast() - '0') * d + m ;
                    d*=10;
                }
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < sb.length(); k++) {
                        stack.add(sb.charAt(k));
                    }
                }
            }else{
                stack.add(c);
            }
        }
        StringBuilder b = new StringBuilder();
        for (char c : stack) {
            b.append(c);
        }
        return b.toString();
    }

    public static void main(String[] args) {

        String str = "0[a]";
        String s = new Solution108().decodeString(str);
        System.out.println(s);
    }
}
