package com.wsy.algorithm.niuke;

import java.util.HashMap;
public class HashTrie{
    private TrieNode root;
    public class TrieNode {
        private HashMap<Character, TrieNode> child = new HashMap<Character, TrieNode>();
        private String word = null;
        public TrieNode() {}
        public String getWord() {
            return word;
        }
        public void setWord(String word) {
            this.word = word;
        }

        public HashMap<Character, TrieNode> getChild() {
            return child;
        }

    }
    public HashTrie(){
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }
    public void insert(String s){
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if(node.child.containsKey(c)){
                node = node.child.get(c);
            }else{
                TrieNode newNode = new TrieNode();
                node.child.put(c,newNode);
                node = newNode;
            }
        }
        node.word = s;
    }
    public boolean startWith(String s){
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if(!node.child.containsKey(c))
                return false;
            node = node.child.get(c);
        }
        return true;
    }
    public boolean search(String s){
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if(!node.child.containsKey(c))
                return false;
            node = node.child.get(c);
        }
        return node.word!=null;
    }

    /**
     * 查找字符的子节点
     */
    public TrieNode findNode(TrieNode node,char c){
        return node.child.get(c);
    }
}
