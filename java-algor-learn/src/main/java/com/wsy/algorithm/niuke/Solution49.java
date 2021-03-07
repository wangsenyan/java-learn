package com.wsy.algorithm.niuke;

import java.util.TreeMap;

public class Solution49 {
    static class Trie {
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

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

}
