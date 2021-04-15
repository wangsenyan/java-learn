package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution103 {
    private int[] indeg;//入度
    private List<List<Integer>> edges;
    public int[]  findOrder(int numCourses, int[][] prerequisites) {
        indeg = new int[numCourses];
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            edges.get(p[1]).add(p[0]);
            ++indeg[p[0]];
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(indeg[i] == 0){
                queue.add(i);
            }
        }
        int visited = 0;
        int[] res = new int[numCourses];
        while (!queue.isEmpty()){
            int u = queue.poll();
            res[visited++] = u;
            for (int v : edges.get(u)) {
                --indeg[v];
                if(indeg[v]==0){
                    queue.offer(v);
                }
            }
        }
        return visited == numCourses ? res : new int[]{};
    }

    public static void main(String[] args) {

        int num = 4;
        int[][] p = new int[][]{{1,0},{0,1},{3,1},{3,2}};
        int[] order = new Solution103().findOrder(num, p);
        System.out.println("");
    }
}
