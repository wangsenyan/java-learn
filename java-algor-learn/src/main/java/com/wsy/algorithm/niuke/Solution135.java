package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

public class Solution135 {
    private char[][] board;
    private int m;
    private int n;
    private boolean[][] seen;
    private String word;
    int[][] direct = new int[][]{{0,-1},{1,0},{0,1},{-1,0}};
    public List<String> findWords(char[][] board, String[] words) {
        //回溯法
        this.board = board;
        List<String> res = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if(exist(word)){
                res.add(word);
            }
        }
        return res;

    }
    public boolean exist(String word) {
        //使用回溯法
        this.word = word;
        this.m = board.length;
        this.n = board[0].length;
        this.seen = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]==word.charAt(0)){
                    seen[i][j]=true;
                    boolean e = helper(1, i, m, j, n);
                    if(e) return true;
                    else seen[i][j]=false;
                }
            }
        }
        return false;
    }
    private boolean helper(int idx,int r,int rl,int c,int cl){
        if(idx==word.length()) return true;
        char w = word.charAt(idx);
        for(int i=0;i<4;i++){
            int row = r + direct[i][1];
            int col = c + direct[i][0];
            if(row>=0 && row<rl && col>=0 && col<cl && board[row][col]==w && !seen[row][col]){
                seen[row][col]=true;
                boolean b = helper(idx + 1, row, rl, col, cl);
                if(b) return true;
                seen[row][col]=false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
