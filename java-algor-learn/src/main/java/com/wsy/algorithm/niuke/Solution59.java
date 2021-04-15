package com.wsy.algorithm.niuke;

import java.util.ArrayList;

public class Solution59 {
    public int calculate(String s) {
        ArrayList<Integer> arr = new ArrayList<>();
        char op = '+';
        int sum = 0;
        int n = s.length();
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)) sum = sum*10 + c-'0';
            if(!Character.isDigit(c) && c!=' ' || i==n-1){
                if(op=='+') arr.add(sum);
                else if(op=='-') arr.add(-sum);
                else if(op=='*') arr.set(arr.size()-1,arr.get(arr.size()-1)*sum);
                else if(op=='/') arr.set(arr.size()-1,arr.get(arr.size()-1)/sum);
                op = c;
                sum = 0;
            }
        }
        return arr.stream().mapToInt(x->x).sum();
    }

    public static void main(String[] args) {
        String s = "3+2*2";
        int calculate = new Solution59().calculate(s);
    }
}
