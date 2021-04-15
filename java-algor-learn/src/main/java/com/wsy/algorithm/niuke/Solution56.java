package com.wsy.algorithm.niuke;

import java.util.LinkedList;

public class Solution56 {
    public int calculate(String s) {
        //栈实现
        LinkedList<String> stack = new LinkedList<>();
        int n = s.length();
        int right = 0;
        while(right < n){
            char c = s.charAt(right);
            if(c=='(') stack.addLast(String.valueOf(c));
            else if(c==')') {//一定要计算
                int val = 0;
                String ch ;
                String nm ;
                do{
                     nm = stack.removeLast();
                     ch = stack.removeLast();
                    val = ch.equals("-")  ? val - Integer.valueOf(nm) : val + Integer.valueOf(nm);
                }while (!ch.equals("("));
                stack.addLast(String.valueOf(val));
            }
            else if(c =='+' || c=='-'){
                stack.addLast(String.valueOf(c));
            }
            else if(digital(c)){
                int num = 0;
                while(right<n && digital(s.charAt(right))){
                    num=num*10+Integer.valueOf(s.charAt(right)-'0');
                    right++;
                }
                right--;
                stack.addLast(String.valueOf(num));
            }
            right++;
        }
        int res = 0;
        boolean add = true;
        while (!stack.isEmpty()){
            String ch = stack.removeFirst();
            if(ch.equals("-")) add = false;
            else if(ch.equals("+")) add=true;
            else res=add?res+Integer.valueOf(ch):res-Integer.valueOf(ch);
        }
        return res;
    }
    private boolean digital(char c){
        return c>='0' && c<='9';
    }

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        int calculate = new Solution56().calculate(s);
        System.out.println(calculate);
    }
}
