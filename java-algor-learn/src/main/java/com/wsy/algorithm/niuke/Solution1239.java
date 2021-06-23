package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution1239 {
    private int r = 0;
    private int[] encode;
    private List<String> arr;
    public int maxLength(List<String> arr) {
        //有重复的不能拼接
        //并查集,排除掉已有重复的,异或,用int表示
        int n = arr.size();
        this.encode = new int[n];
        this.arr = arr;
        for(int i = 0; i < n; i++){
            String s = arr.get(i);
            int code = 0;
            for(char c : s.toCharArray()){
                //单字符串有重复的字母
                int b = 1 << (c-'a');
                if((code & b) > 0){
                    code = 0;
                    break;
                }
                code += b;
            }
            encode[i] = code;
        }
        reverse(0, 0, 0);
        return r;
    }
    private void reverse(int i,int en,int len){
        if(i == arr.size()) {
            r = Math.max(r,len);
            return;
        }
        int l = arr.get(i).length();
        //包含当前和不包含当前的
        if(encode[i]!=0){//如果当前i满足,
            if((en&encode[i])==0){
                reverse(i+1,en|encode[i],len+l);
            }
        }//跳过encode[i]
        reverse(i+1,en,len);
    }
    public static void main(String[] args) {

        List<String> arr = Arrays.asList("ab","cd","cde","cdef","efg","fgh","abxyz");
        int i = new Solution1239().maxLength(arr);
        System.out.println(i);
    }
}
