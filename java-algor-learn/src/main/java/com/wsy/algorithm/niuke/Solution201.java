package com.wsy.algorithm.niuke;

public class Solution201 {
    public int rangeBitwiseAnd(int left, int right) {
        //[left,right]按位与,在递增过程中,只要位置变过,肯定为0,变成了求left与right最高位相同的数
        int offset = 0;
        while(left!=right){
            left>>=1;
            right>>=1;
            offset++;
        }
        return left<<offset;
    }

    public static void main(String[] args) {
        int a = 2147483646;
        int b = 2147483647;
        int i = new Solution201().rangeBitwiseAnd(a, b);

        System.out.println(i);
    }
}
