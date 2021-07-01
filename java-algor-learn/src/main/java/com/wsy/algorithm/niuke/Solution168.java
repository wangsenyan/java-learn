package com.wsy.algorithm.niuke;

public class Solution168 {
    public String convertToTitle(int columnNumber) {
        //1~26 A~Z 0~25
        //(0~25) + 1
        //(26-1) % 26 + 1
        StringBuilder sb = new StringBuilder();
        while(columnNumber>0){
            int s = (columnNumber-1) % 26;
            sb.append(Character.toChars('A' + s));
            columnNumber = (columnNumber - 1)/26;
            //columnNumber/=26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = new Solution168().convertToTitle(1);
        System.out.println(s);
    }
}
