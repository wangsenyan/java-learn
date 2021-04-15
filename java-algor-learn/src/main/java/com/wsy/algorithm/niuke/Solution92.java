package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution92 {
    private List<String> wordDict;
    private int wordLen;
    private boolean[] seen;
    static class Trie {
        private int size = 'z'-'a' +1;
        private Trie.Node[] root;//根节点,index=c - 'a', value=next
        /** Initialize your data structure here. */
        public Trie() {
            this.root = new Trie.Node[size];
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            int i = 0;
            Trie.Node[] head = root;
            while (i<word.length()){
                int c = word.charAt(i)  -'a';
                if(head[c] == null){//如果head为空
                    head[c] = new Trie.Node();
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
            Trie.Node[] head = root;
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
            Trie.Node[] head = root;
            while (i < prefix.length()){
                int c = prefix.charAt(i) - 'a';
                if(head[c]==null) return false;
                head = head[c].next;
                i++;
            }
            return true;
        }
        class Node {
            Trie.Node[] next;
            boolean rt;//表示是否以此为根节点
            public Node(){
                this.rt = false;
                this.next = new Trie.Node[size];
            }
        }
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] spd = new boolean[n+1];
        Set<String> set = new HashSet<>(wordDict);
        spd[0] = true;
        for(int i = 1; i<= n; i++){
            for(int j = i-1;j >=0;j--){
                if(spd[j] && set.contains(s.substring(j,i))){
                    spd[i] = true;
                    break;
                }
            }
        }
        return spd[n];
    }
    private List<Integer> kmp(String s,String p){
        List<Integer> idx = new ArrayList<>();
        int sl = s.length();
        int pl = p.length();
        int[] next = next(p);
        int i = 0;
        int j = 0;
        while (i<sl){
            if(j==-1 || s.charAt(i) == p.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
            if(j==pl){
                for (int k = i-j; k <=i; k++) {
                    seen[i-j]=true;
                }
                idx.add(i-j);
                j=0;
            }
        }
        return idx;
    }
    private int[] next(String p){
        int pl = p.length();
        int[] next = new int[pl];
        int k = -1;
        int j = 0;
        while (j<pl){
            if(k==-1 || p.charAt(j)==p.charAt(k)){
                ++k;
                ++j;
                next[j] = k;
            }else{
                k = next[k];
            }
        }
        return next;
    }
    public static void main(String[] args) {

        List<String> word = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        long l = System.nanoTime();
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        boolean b = new Solution92().wordBreak(s, word);
        long l1 = System.nanoTime();
        System.out.println(l1 - l);
    }
}
