package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

public class NumArray {
    int[] nums;
    int n;
    int LEN = 100;//100个一组
    List<Integer> G;//分组
    public NumArray(int[] nums) {
        this.G = new ArrayList<>();
        this.nums = nums;
        this.n = nums.length;
        for (int i = 0; i < n; i += LEN) {
            int sum = 0;
            for (int j = i; j < i + LEN && j < n; j++) {
                sum+=nums[j];
            }
            G.add(sum);
        }
    }

    public void update(int index, int val) {
        //只更新当前的数字
        int g = index/LEN;//当前位置
        int s = G.get(g);//当前和
        int ns = s - nums[index] + val;
        G.set(g,ns);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        int gs = left/LEN;
        int es = right/LEN;
        int sm = 0;
        if(gs==es){
            for (int i = left; i <= right; i++) {
                sm+=nums[i];
            }
        }else{
            //完整版的
            for (int i = gs + 1; i < es; i++) {
                sm += G.get(i);
            }
            //前面的
            for (int i = left; i < (gs + 1) * LEN ; i++) {
                sm += nums[i];
            }
            for (int i = es * LEN; i <= right ; i++) {
                sm += nums[i];
            }
        }
        return sm;
    }

    public static void main(String[] args) {
        int[] nums = {0,9,5,7,3};

        NumArray numArray = new NumArray(nums);
        numArray.sumRange(4,4);
        numArray.sumRange(2,4);
        numArray.sumRange(3,3);
        numArray.update(4,5);
        numArray.update(1,7);
        numArray.update(0,8);
        numArray.sumRange(1,2);
        numArray.update(1,9);
        numArray.sumRange(4,4);
        numArray.update(3,4);
    }
}
