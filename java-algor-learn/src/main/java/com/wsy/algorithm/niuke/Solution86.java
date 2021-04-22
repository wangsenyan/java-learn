package com.wsy.algorithm.niuke;

import com.wsy.algorithm.utils.ReadFile;

import java.util.LinkedList;

class Solution86 {
    public int minJump(int[] jump) {
        //可以从i到【0,i+jump[i]]
        //最小次数,bfs,回边怎么办?只要经过,下次经过肯定多跳了
        int n = jump.length;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        int step = 0;
        int mx = 1;
        while(!queue.isEmpty()){
            step++;
            int len = queue.size();
            for(int i=0;i<len;i++){
                int e = queue.removeFirst();
                if(jump[e] + e>=n) return step;
                if(jump[e] + e>=mx)
                    queue.addLast(jump[e] + e);//最远距离
                for (int j = mx ; j < e; j++) {
                    queue.add(j);
                    mx = Math.max(mx,e+1);
                }
            }
        }
        return step;
    }

    public static void main(String[] args) {
        String fileName = "86_1.txt";
        String str = ReadFile.readJsonFile(fileName);
        String[] split = str.split(",");
        int[] arr = new int[split.length];
        for ( int i = 0; i < split.length; i++) {
            arr[i] = Integer.valueOf(split[i]);
        }
        //int[] jump = new int[]{4,6,10,8,3,5,3,5,7,8,6,10,3,7,3,10,7,10,10,9,1,4,7,4,8,6,9,8,8,2,7,2,4,5,4,3,3,2,2,2,3,4,4,1,1,5,6,8,1,2};
        int i = new Solution86().minJump(arr);
        System.out.println(i);
    }
}