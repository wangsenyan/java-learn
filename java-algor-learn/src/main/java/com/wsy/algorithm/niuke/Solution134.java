package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution134 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //转换为无向图
        //转换关系,hot -> lot,dot,
        //如何快速判断是否可以转换,bfs
        //将其转换为int,异或后,为2^x
        //预处理n^2,不预处理
        Set<String> set = new HashSet<>(wordList);
        LinkedList<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int r = 0;
        while (!queue.isEmpty()){
            int s = queue.size();
            r++;
            for (int i = 0; i < s; i++) {
                String u = queue.poll();
                if(u.equals(endWord)) return r;
                Iterator<String> it = set.iterator();
                while (it.hasNext()){
                    String v = it.next();
                    if(helper(u,v)){
                        it.remove();
                        queue.add(v);
                    }
                }
            }
        }
        return 0;
    }
    private boolean helper(String s1,String s2){
        int l1 = s1.length();
        int l2 = s2.length();
        if(l1!=l2) return false;
        int k = 0;
        for (int i = 0; i < l1; i++) {
            if(s1.charAt(i)!=s2.charAt(i)){
                k++;
            }
            if(k >1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String b = "hit";
        String e = "cog";
        List<String> words = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int i = new Solution134().ladderLength(b, e, words);
        System.out.println(i);
    }
}
