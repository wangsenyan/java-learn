package com.wsy.algorithm.niuke;

public class Solution260 {
    // [1,2,1,3,2,5]
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor^=num;
        }
        //根据某个位上进行分组
        int exp = 1;
        while (xor!=0){
            if((xor&exp)!=0) break;
            exp<<=1;
        }
        //分组
        int[] res = new int[2];
        for (int num : nums) {
            if((num&exp)!=0) res[0]^=num;
            else res[1]^=num;
        }
        return res;
    }
    public int singleNumber1(int[] nums) {
        int a = 0;
        int b = 0;
        for (int num : nums) {
            a = (a ^ num) & ~b; //num
            b = (b ^ num) & ~a; // 0
        }
        return a;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,3,4,16,32};
        int[] ints = new Solution260().singleNumber(nums);
        System.out.println(ints);
    }
}
