package com.wsy.algorithm.niuke;

import java.util.*;
import java.util.stream.Collectors;

public class Solution35 {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        //状态压缩,对word
        int n = words.length, m = puzzles.length;
        HashMap<Integer,Integer> hash = new HashMap();
        int[] res = new int[m];
        for(int i=0;i<n;i++){
            int t = 0;
            for(char w : words[i].toCharArray()) t |= 1<<(w-'a');
            if(hash.containsKey(t)) hash.put(t,hash.get(t)+1);
            else hash.put(t,1);
        }
        for(int i=0;i<m;i++){
            int k = 0,t=puzzles[i].charAt(0)-'a';
            for(char w:puzzles[i].toCharArray()) k|=1<<(w-'a');
            for(int x=k; x!=0 ; x=(x-1)&k ){//puzzle的子集
                if( (x&(1<<t))!=0) {
                    Integer num = hash.get(x);
                    if(num!=null) res[i]+=num;
                };
            }
        }
        return Arrays.stream(res)
                .boxed()
                .collect(Collectors.toList());
    }

}
