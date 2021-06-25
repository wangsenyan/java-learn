package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

/**
 * 1~9组合中,k个数和为n的组合
 * 和为 1~36
 */
public class Solution216 {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.res = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        dfs(0,0,k,0,n,nums);
        return res;
    }

    private void dfs(int i,int ko,int k,int no,int n,List<Integer> nums){
        //什么时候终止???当i > k 或 m > n,终止并不继续
        if(ko > k || no > n || i > 9) return;
        if(ko==k && no==n) {
            res.add(new ArrayList<>(nums));
            return;
        }
        //前进一步,不加都失败了,加了肯定失败???
        dfs(i+1,ko,k,no,n,nums);
        //前进一部,加
        nums.add(i+1);
        dfs(i+1,ko+1,k,no+i+1,n,nums);
        nums.remove(nums.size() - 1);
    }

    public static void main(String[] args) {
        int k = 3;
        int n = 7;
        List<List<Integer>> lists = new Solution216().combinationSum3(k, n);

        System.out.println(lists);
    }
}
