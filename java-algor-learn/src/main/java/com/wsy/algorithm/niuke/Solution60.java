package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Solution60 {
    private String[] s ;
    private int last;
    public boolean isValidSerialization(String preorder) {
        //分割
        //递归
        s = preorder.split(",");
        //用栈
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length; i++) {
            if(!s[i].equals("#")) stack.push(s[i]);
            else{
                int size = stack.size();
                while (size>1 && stack.get(size-1).equals("#") && !stack.get(size-2).equals("#")){
                    stack.pop();
                    stack.pop();
                    size = stack.size();
                }
                stack.push("#");
            }
        }
        return stack.size() == 1 && stack.pop()=="#";
    }

    public static void main(String[] args) {
        String s = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        boolean validSerialization = new Solution60().isValidSerialization(s);
    }
}
