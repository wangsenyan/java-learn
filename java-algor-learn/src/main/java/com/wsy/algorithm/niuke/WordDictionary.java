package com.wsy.algorithm.niuke;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 */
public class WordDictionary {
    //Tire
    class Node{
        char c;
        boolean end;
        Node[] child;
        //Map<Character,Node> child;
        public Node(){
            this.child = new Node[26];
            //this.child = new HashMap<>();
        }
        public Node(char c){
            this.c = c;
            this.child = new Node[26];
            //this.child = new HashMap<>();
        }
    }
    private Node head;
    /** Initialize your data structure here. */
    public WordDictionary() {
       this.head = new Node();
    }

    public void addWord(String word) {
        Node node = head;
        for (char c : word.toCharArray()) {
            int o = c - 'a';
            if(node.child[o]!=null){
                node = node.child[o];
            }else{
                Node n = new Node(c);
                node.child[o] = n;
                node = n;
            }
        }
        node.end = true;
    }

    public boolean search(String word) {
        return search(head,word,0);
    }
    private boolean search(Node node,String word,int idx){
        if(idx == word.length())
            return node.end;
        char c = word.charAt(idx);
        if(c == '.'){
            for (Node n : node.child) {
                if(n!=null)
                   if(search(n,word,idx + 1)) return true;
            }
            return false;
        }else if(node.child[c-'a']!=null){
            return search(node.child[c-'a'],word,idx+1);
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mad");
        dictionary.addWord("pad");
        dictionary.addWord("bad");
        dictionary.search(".ad");
        dictionary.search("b..");

    }
}