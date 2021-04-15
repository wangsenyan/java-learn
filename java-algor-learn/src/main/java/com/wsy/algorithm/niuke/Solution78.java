package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution78 {
    int len;
    char[] charArray;
    private Set<String> express;
    public List<String> removeInvalidParentheses(String s) {
        len = s.length();
        charArray = s.toCharArray();
        express = new HashSet<>();
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < len; i++) {
            if(charArray[i] == '(') {
                leftRemove++;
            }else if(charArray[i] == ')'){
                if(leftRemove == 0) {
                    rightRemove++;
                }
                if(leftRemove > 0) {
                    leftRemove--;
                }
            }
        }
        StringBuilder str = new StringBuilder();
        dfs(0,0,0,leftRemove,rightRemove,str);
        return  new ArrayList<>(express);
    }
    private void dfs(int index,int leftCount,int rightCount,int leftRemove,int rightRemove,StringBuilder str){
        if(index == len){
            if(leftRemove == 0 && rightRemove == 0){
                express.add(str.toString());
            }
            return;
        }
        char c = charArray[index];
        if(c == '(' && leftRemove > 0){
            dfs(index + 1,leftCount,rightCount,leftRemove-1,rightRemove,str);
        }
        if(c == ')' && rightRemove > 0){
            dfs(index + 1,leftCount,rightCount,leftRemove,rightRemove-1,str);
        }
        str.append(c);
        //保存
        if(c != '(' && c != ')'){
            dfs(index + 1,leftCount,rightCount,leftRemove,rightRemove,str);
        }else if(c == '('){
            dfs(index + 1,leftCount+1,rightCount,leftRemove,rightRemove,str);
        }else if(leftCount > rightCount){
            dfs(index + 1,leftCount,rightCount+1,leftRemove,rightRemove,str);
        }
        str.deleteCharAt(str.length() - 1);
    }
}
