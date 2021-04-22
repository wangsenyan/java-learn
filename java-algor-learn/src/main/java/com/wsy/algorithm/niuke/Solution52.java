package com.wsy.algorithm.niuke;

import java.util.LinkedList;

public class Solution52 {
    public String removeDuplicates(String S) {
        //删除回文
        int n = S.length();
        for(int i=0;i<n-1;i++){
            if(S.charAt(i)==S.charAt(i+1))
                return removeDuplicates(S.substring(0,i) + S.substring(i+2,n));
        }
        return S;
    }
    public String removeDuplicates1(String S) {
        //删除回文
        int n = S.length();
        LinkedList<Character> stack = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(!stack.isEmpty() && stack.getLast() == S.charAt(i)){
                stack.removeLast();
            }else{
                stack.addLast(S.charAt(i));
            }
        }
        StringBuilder s = new StringBuilder();
        for (Character c : stack) {
            s.append(c);
        }
        return s.toString();

    }
    public static void main(String[] args) {
        String abbacabcd = new Solution52().removeDuplicates1("abbacabcd");
    }
}
