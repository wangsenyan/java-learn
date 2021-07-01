package com.wsy.algorithm.niuke;

public class Solution289 {
    private int[][] board;
    private int m;
    private int n;
    private final int[] direct = {-1,0,1};
    public void gameOfLife(int[][] board) {
        //只需记住[[0,1,2],[3]] 四个位置
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int d = calculate(i,j);
                int t = 0 ;
                if(d == 3) t = 1;
                if(d == 2) t = board[i][j];
                board[i][j] += t<<1;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j]>>=1;
            }
        }
    }
    private int calculate(int y,int x){
        int r = 0;
        for (int i = 0; i < 3; i++) {
            int yi = y + direct[i];
            for (int j = 0; j < 3; j++) {
                int xi = x + direct[j];
                if(yi >= 0 && yi < m && xi >= 0 && xi < n){
                        r += board[yi][xi]&1;
                }
            }
        }
        r-= board[y][x];
        return r;
    }

    public static void main(String[] args) {
        int[][] board = {{1,1},{1,0}};
        new Solution289().gameOfLife(board);
    }
}
