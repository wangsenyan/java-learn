package com.wsy.algorithm.niuke;

import java.util.*;

public class Solution138 {
    private List<List<String>> _res;
    private Map<String,Set<String>> _map;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //dfs,每一个可能的都遍历一遍
        //分别遍历
        this._res = new ArrayList<>();
        this._map = new HashMap<>();

        if(!wordList.contains(endWord))
            return _res;
        //遍历List,寻找匹配项
        wordList.remove(beginWord);
        wordList.add(0,beginWord);
        for (String s : wordList) {
            _map.put(s,new HashSet<>());
        }
        //bfs
        int n = wordList.size();
        for (int i = 0; i < n; i++) {
            String s1 = wordList.get(i);
            for (int j = i + 1; j < n; j++) {
                String s2 = wordList.get(j);
                if(helper(s1,s2)){
                    _map.get(s1).add(s2);
                    _map.get(s2).add(s1);
                }
            }
        }

        //bfs
        List<List<String>> queue = new ArrayList<>();
        List<String> sub = new ArrayList<>();
        sub.add(beginWord);
        queue.add(sub);//单线程的
        boolean end = false;
        while (!queue.isEmpty() && !end){
            int m = queue.size();
            for (int i = 0; i < m; i++) {
                List<String> lst = queue.remove(0);//从最后一个
                String u = lst.get(lst.size() - 1);
                if(u.equals(endWord)) {
                    end = true;
                    _res.add(lst);
                }
                if(end) continue;
                Set<String> vs = _map.get(u);
                for (String v : vs) {
                    if(!lst.contains(v)){
                        List<String> nLst = new ArrayList<>(lst);
                        nLst.add(v);
                        queue.add(nLst);
                    }
                }
            }
        }
        return _res;
    }
    private boolean helper(String s1,String s2){
        int l1 = s1.length();
        int k = 0;
        for (int i = 0; i < l1; i++) {
            if(s1.charAt(i)!=s2.charAt(i)){
                k++;
            }
            if(k >1) return false;
        }
        return true;
    }
//    private void dfs(String u,List<String> sub,int deep){
//        if(_stop && _deep < deep) return;
//        if(u.equals(_endWord)){
//            if(!_stop || deep == _deep){
//                _deep = deep;
//                _stop = true;
//            }else {
//                _res.clear();
//                _deep = deep;
//            }
//            _res.add(new ArrayList<>(sub));
//            return;
//        }
//        Set<String> vs = _map.get(u);
//        for (String v : vs) {
//            if(!sub.contains(v)){
//                sub.add(v);//避免进入死循环
//                dfs(v,sub,deep + 1);
//                sub.remove(sub.size() - 1);
//            }
//        }
//    }

    public static void main(String[] args) {
        String b = "hot";
        String e = "dog";
        List<String> words =  new ArrayList<>(Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot"));
        List<List<String>> ladders = new Solution138().findLadders(b, e, words);
        System.out.println(ladders);
    }
}
