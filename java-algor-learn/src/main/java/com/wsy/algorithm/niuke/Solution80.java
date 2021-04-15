package com.wsy.algorithm.niuke;
class Solution80 {
    public int maximalRectangle(char[][] matrix) {
        //每行的最大面积
        //整体的最大面积
        int m = matrix.length;
        if(m==0 || matrix[0].length == 0) return 0;
        int n = matrix[0].length;
        //点[i,j] 在多个方向 可能相同 [i,j] -> [i+x,j+y]
        //到某个点的最大?
        int[][] area_row = new int[m][n+1];
        //int[][] area = new int[m][n+1];
        int max_area = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                area_row[i][j+1]= (matrix[i][j] == '1' ? area_row[i][j] + 1 : 0);
                int min_row = area_row[i][j+1];
                max_area = Math.max(max_area,min_row);
                for(int k=i-1;k>=0;k--){
                    min_row = Math.min(min_row,area_row[k][j+1]);
                    if(min_row ==0 ) break;
                    int area = min_row * (i - k +1);
                    max_area = Math.max(area,max_area);
                }
            }
        }
        return max_area;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'0','0','0','0','0','0','1'},{'0','0','0','0','1','1','1'},{'1','1','1','1','1','1','1'},{'0','0','0','1','1','1','1'}};
        System.out.println(new Solution80().maximalRectangle(matrix));
    }
}