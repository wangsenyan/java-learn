package com.wsy.algorithm.niuke;

import java.util.Optional;
import java.util.PriorityQueue;

public class Solution1738 {
    public int kthLargestValue(int[][] matrix, int k) {
        //先计算
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] rs = new int[m][n];
        //用最小堆保存k个,当堆顶大于当前元素,删除
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0 ;i < m ; i++){
            for(int j = 0; j < n;j++){
                rs[i][j] ^= matrix[i][j];
                if(i > 0) rs[i][j] ^= rs[i-1][j];
                if(j > 0) rs[i][j] ^= rs[i][j-1];
                if(i > 0 && j > 0) rs[i][j] ^= rs[i-1][j-1];
                if(pq.size() < k) pq.offer(rs[i][j]);
                else if(pq.peek() < rs[i][j]){
                    pq.poll();
                    pq.offer(rs[i][j]);
                }
            }
        }
        return pq.peek();
        //再排序
    }

    public static void main(String[] args) {
        int[][] matrix = {{8,10,5,8,5,7,6,0,1,4,10,6,4,3,6,8,7,9,4,2}};
        int k = 2;
        int i = new Solution1738().kthLargestValue(matrix, k);
        System.out.println(i);
    }
}
