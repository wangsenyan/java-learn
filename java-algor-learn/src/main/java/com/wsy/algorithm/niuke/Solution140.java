package com.wsy.algorithm.niuke;

public class Solution140 {
    public String convert(String s, int numRows) {
        int n = s.length();
        //其余每行有3个
        //找到比n大的
        int v = (n * numRows) / (2 * (numRows - 1));
        if(v % 2 == 0) v+=1; //v表示最大的行数
        char[][] area = new char[numRows][v];
        int i = 0 ;
        int k = 0 ;
        while (i < n){
            //向下
            for (int j = 0; j < numRows-1 && i < n; j++) {
                area[j][k] = s.charAt(i++);
            }
            //斜向上
            for (int j = numRows-1; j > 0 && i < n; j--) {
                area[j][k++]=s.charAt(i++);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            for (int l = 0; l < v; l++) {
                if(area[j][l]!= 0){
                    sb.append(area[j][l]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String s = "PAYPALISHIRING" ;
        int n = 4;
        String convert = new Solution140().convert(s, n);
        System.out.println(convert);
    }
}
