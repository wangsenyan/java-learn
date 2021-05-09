package com.wsy.algorithm.niuke;

import java.util.*;
import java.util.stream.Collectors;

public class Solution119 {
    private char[][] board;
    private Trie trie;
    //private List<String> res;
    private Set<String> res;
    int[][] direct = new int[][]{{0,-1},{1,0},{0,1},{-1,0}};
    private int m;
    private int n;
    private boolean[][] seen ;
    public List<String> findWords(char[][] board, String[] words) {
        //回溯法
        this.board = board;
        this.res = new HashSet<>();
        this.trie = new Trie();
        this.m = board.length;
        this.n = board[0].length;
        this.seen = new boolean[m][n];
        //从顶点出发,查找可以回溯的???
        //构造trie
        
        for (String word : words) {
            trie.insert(word);
        }
        //回溯?
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String s = "";
                helper(i,j,s);
            }
        }
        return new ArrayList<>(res);
    }
    private void helper(int r,int c,String str){
        if(!seen[r][c]){
            str += board[r][c];
            if(trie.search(str)) {
                res.add(str);
            }
            if(trie.startsWith(str)){
                seen[r][c] = true;
                for (int i = 0; i < 4; i++) {
                    int x = r + direct[i][0];
                    int y = c + direct[i][1];
                    if(x>=0 && x<m && y>=0 && y<n && !seen[x][y]){
                        helper(x,y,str);
                    }
                }
                seen[r][c] = false;
            }
        }
    }

    public static void main(String[] args) {
        char[][] b = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] w = {"oath","pea","eat","rain"};
        List<String> words = new Solution119().findWords(b,w);
        System.out.println(words);
    }
}
class Trie {
    private int size = 'z'-'a' +1;
    private Node[] root;//根节点,index=c - 'a', value=next
    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node[size];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        int i = 0;
        Node[] head = root;
        while (i<word.length()){
            int c = word.charAt(i)  -'a';
            if(head[c] == null){//如果head为空
                head[c] = new Node();
            }
            if(i == word.length() - 1)
                head[c].rt = true;
            head = head[c].next;
            i++;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int i = 0;
        Node[] head = root;
        while (i < word.length()){
            int c = word.charAt(i) - 'a';
            if(head[c]==null) return false;
            if(i==word.length() - 1 && !head[c].rt) return false;
            head = head[c].next;
            i++;
        }
        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int i = 0;
        Node[] head = root;
        while (i < prefix.length()){
            int c = prefix.charAt(i) - 'a';
            if(head[c]==null) return false;
            head = head[c].next;
            i++;
        }
        return true;
    }
    class Node {
        Node[] next;
        boolean rt;//表示是否以此为根节点
        public Node(){
            this.rt = false;
            this.next = new Node[size];
        }
    }
}
