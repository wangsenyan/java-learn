package com.wsy.algorithm.niuke;

import ch.qos.logback.core.pattern.parser.Node;

import java.util.*;

public class Solution10 {
    public int longestValidParentheses(String s) {
        //使用栈
        //linklist
        class Node{
            private char key;
            private int index;
            public Node(char key, int index) {
                this.key = key;
                this.index = index;
            }
        }
        LinkedList<Node> stack = new LinkedList<>();
        int len = s.length();
        int[] cs = new int[len];
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == '('){
                stack.addLast(new Node('(',i));
            }
            if(s.charAt(i) == ')'){
                if(!stack.isEmpty()){
                    Node c = stack.removeLast();
                    if(c.key=='('){
                        cs[i] = i - c.index+1;
                        if(c.index > 0 && cs[c.index - 1]>0)
                            cs[i] = cs[c.index -1] + cs[i];
                    }
               }
           }
        }
        return  cs.length >0? Arrays.stream(cs).max().getAsInt():0;
    }
    public int longestValidParentheses1(String s) {
        int len  = s.length();
        int[] cs = new int[len];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == '('){
                stack.addLast(i);//肯定是最近的
            }
            if(s.charAt(i) == ')'){
                if(!stack.isEmpty()){
                    Integer c = stack.removeLast();
                    cs[i] = i - c + 1;
                    if(c>0 && cs[c-1]>0)
                        cs[i] = cs[c-1] + cs[i];
                }
            }
        }
        return cs.length >0? Arrays.stream(cs).max().getAsInt():0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution10().longestValidParentheses1("()(())"));
    }
}
