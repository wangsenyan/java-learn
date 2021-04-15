package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution109 {
    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people,(a,b)->{
            return a[1] - b[1] == 0 ? a[0] - b[0] : a[1] - b[1];
        });
        //对于0的排序是正确的
        int n = people.length;
        ArrayList<int[]> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(people[i][1]==0){
                arr.add(people[i]);
            }else{
                int b = people[i][1];
                int j = 0;
                for (; j < arr.size() ; j++) {
                    if(arr.get(j)[0]>=people[i][0]) b--;
                    if(b< 0) break;
                }
                arr.add(j,people[i]);
            }
        }
        int[][] res = new int[n][2];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }
        return res;
    }
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(a,b)->{
            return a[0] == b[0]  ? a[1] - b[1] : b[0] - a[0];
        });
        //对于0的排序是正确的
        int n = people.length;
        ArrayList<int[]> arr = new ArrayList<>();
        for (int[] p : people) {
            arr.add(p[1],p);
        }
        return arr.toArray(new int[arr.size()][]);
    }
    public static void main(String[] args) {
        int[][] peopeo = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] ints = new Solution109().reconstructQueue(peopeo);
        System.out.println("a");
    }
}
