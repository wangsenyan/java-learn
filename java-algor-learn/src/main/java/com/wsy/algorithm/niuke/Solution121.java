package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

//class TrieNode {
//    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
//    String word = null;
//    public TrieNode() {}
//}

class Solution121 {
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        HashTrie trie = new HashTrie();
        for (String word : words) {
            trie.insert(word);
        }
        HashTrie.TrieNode root = trie.getRoot();

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.getChild().containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, HashTrie.TrieNode parent) {
        Character letter = this._board[row][col];
        HashTrie.TrieNode currNode = parent.getChild().get(letter);

        // check if there is any match
        if (currNode.getWord() != null) {
            this._result.add(currNode.getWord());
            currNode.setWord(null);
        }

        // 标记当前节点已经被访问过
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.getChild().containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // 返回后供其他节点访问
        this._board[row][col] = letter;

        //为何要剪枝?从末尾返回后,不会再被访问了
        if (currNode.getChild().isEmpty()) {
            parent.getChild().remove(letter);
        }
    }

    public static void main(String[] args) {
        char[][] b = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] ws = {"oath","pea","eat","rain"};
        List<String> words = new Solution121().findWords(b, ws);
    }
}