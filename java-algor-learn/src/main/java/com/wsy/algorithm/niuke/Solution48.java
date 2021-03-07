package com.wsy.algorithm.niuke;

import java.util.LinkedList;
import java.util.List;

class Solution48 {
    List<List<String>> ans ;
    String str;
    public List<List<String>> partition(String s) {
        ans = new LinkedList<>();
        str = s;
        int len = s.length();
        LinkedList<String> res = new LinkedList<>();
        dfs(0,len-1,len,res);
        return ans;
        //
    }
    private void dfs(int left,int right,int len,LinkedList<String> res){
        if(left == len){
            ans.add(new LinkedList<>(res));
            return;
        }
        for(int i=left;i<=right;i++){
            if(checked(left,i)){
                res.addLast(str.substring(left,i+1));
                dfs(i+1,right,len,res);
                res.removeLast();
            }
        }
    }
    boolean checked(int left,int right){
        //System.out.println(left + "-" +right);
        while(left<right){
            if(str.charAt(left)!=str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution48().partition("aab"));

    }
}
