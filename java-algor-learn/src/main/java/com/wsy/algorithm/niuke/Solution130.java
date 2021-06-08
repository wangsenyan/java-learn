package com.wsy.algorithm.niuke;

class Solution130 {
    private char[][] board;
    private int[][] direct = {{-1,0},{0,1},{1,0},{0,-1}};//逆时针
    private int m;
    private int n;
    public void solve(char[][] board) {
        //从边界处的O开始dfs,遇到的进行标记为'T'
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        for (int i = 0; i < m; i++) {
            if(board[i][0]=='O'){
                dfs(i,0);
            }
            if(board[i][n-1]=='O'){
                dfs(i,n-1);
            }
        }
        for (int i = 0; i < n; i++) {
            if(board[0][i]=='O'){
                dfs(0,i);
            }
            if(board[m-1][i]=='O'){
                dfs(m-1,i);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]=='T') board[i][j]='X';
            }
        }
    }
    public void dfs(int r,int c){
        //从[r,c]开始,进行dfs
        board[r][c] = 'T';
        for (int[] d : direct) {
            int y = r + d[0];
            int x = c + d[1];
            if(x>=0 && x < n && y>=0 && y<m && board[y][x]=='O'){
                dfs(y,x);
            }
        }
    }
}