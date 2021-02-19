package com.wsy.algorithm.niuke;

public class Solution11 {
    public int largestRectangleArea(int[] heights) {
        //面积 = 连续个数 * 最小值
        //分别包含当前的最大值
        int len = heights.length;
        int mx = 0;
        int[][] hl = new int[len+1][2];

        for (int i = 1; i < len+1; i++) {
            //如果下一个大于上一个,那么,最大值从当前和上一个+x比
            //如果下一个小于上一个,那么,最大值为此到上一个此高度比
            int h = heights[i-1];
            if(hl[i-1][0] <= h){
                int s = (hl[i-1][1] + 1) * hl[i-1][0];
                if( s <= h ){
                    hl[i][0] = h;
                    hl[i][1] = 1;
                }else{
                    hl[i][0] = hl[i-1][0];
                    hl[i][1] = hl[i-1][1] + 1;
                }
            }else{
                int step = hl[i-1][1];
                while (i-1-step>0 && hl[i-1-step][0]>=h){
                    step+=hl[i-1-step][1];
                }
                int s1 = (step + 1)*h;
                int s2 = (step + 1 + hl[i-1-step][1]) * hl[i-1-step][0];
                if(s1 < s2){
                    hl[i][0] = hl[i-1-step][0];
                    hl[i][1] = step + 1 + hl[i-1-step][1];
                }else{
                    hl[i][0] = h;
                    hl[i][1] = step + 1;
                }
            }
            mx = mx > (hl[i][0] * hl[i][1] ) ? mx : (hl[i][0] * hl[i][1] );
        }
        return mx;
    }
    public int largestRectangleArea1(int[] heights) {
        int maxarea = 0;
        int len = heights.length;
        for (int i=0;i<len;i++){
            if (heights[i]*len<=maxarea) continue;// 如果当前元素的值×长度都小于当前maxarea则没有继续查找的必要,避免超时
            int left = i;
            int right = i;

            while (left-1>=0&&heights[left-1]>=heights[i]) left--;
            while (right+1<len&&heights[right+1]>=heights[i]) right++;


            int area = (right-left+1)*heights[i];
            if (area>maxarea) maxarea = area;

        }

        return maxarea;
    }

    public static void main(String[] args) {
        int[] ints = {1,2,3,4,5};
        System.out.println(new Solution11().largestRectangleArea(ints));
    }
}
