package com.wsy.algorithm.niuke;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 630. 课程表 III https://leetcode-cn.com/problems/course-schedule-iii/
 */
public class Solution104 {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses,(a,b)-> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        int last = 0;
        for (int i = 0; i < courses.length ; i++) {
            int[] c = courses[i];
            if(last + c[0] <= c[1]){
                pq.offer(c[0]);
                last+=c[0];
            }else{
                if(!pq.isEmpty() && pq.peek() > c[0]){
                    last+=c[0] - pq.poll();
                    pq.offer(c[0]);
                }
            }
        }
        return pq.size();
    }

    public static void main(String[] args) {
        int[][] courses = new int[][]{{5,5},{2,6},{4,6}};
        System.out.println(new Solution104().scheduleCourse(courses));
    }
}
