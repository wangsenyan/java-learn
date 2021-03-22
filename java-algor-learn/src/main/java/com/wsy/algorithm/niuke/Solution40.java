package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution40 {
    public List<Integer> findSubstring(String s, String[] words) {
        //滑动窗口,固定长度 words.length * words[0].length()
        //words[0].length() 一组进行前移
        //对words进行处理,hashMap<String,Integer> wordCount;
        //num记录已经匹配的个数 words.length - !!wordCount[word]
        //对单词进行压缩??
        int ws = words.length;//words数组长度
        int wl = words[0].length();//word单词长度
        List<Integer> res = new ArrayList<Integer>();
        HashMap<String,Integer> wordCount = new HashMap<>();
        for(int i=0;i<ws;i++){
            Integer word = wordCount.get(words[i]);
            wordCount.put(words[i],word==null?1 :word+1);
        }
        int sl = s.length();
        if(sl - ws * wl < 0) return res;
        int pHash = 0;
        for(int i=0;i<ws;i++){
            for(int j=0;j<wl;j++)
                pHash+=(words[i].charAt(j)-'a');
        }
        int wordHash = 0;
        int[] windowHash = new int[sl - ws * wl];
        int k = ws * wl;
        for(int i=0;i< s.length(); i++){
            if(i<k) {
                wordHash+=(s.charAt(i)-'a');
            }else{
                wordHash+=(s.charAt(i)-'a');
                wordHash-=(s.charAt(i-k)-'a');
            }
            if(i>=k-1 && wordHash == pHash){
                //对[i-k+1,i]区间进行匹配
                HashMap<String, Integer> cp = new HashMap<>(wordCount);
                int j = 0;
                for(;j<k;j+=wl){
                    int st = i-k+1+j;
                    String wd = s.substring(st,st+wl);
                    Integer wdCount = cp.get(wd);
                    if(wdCount == null || wdCount<=0) break;
                    cp.put(wd,wdCount-1);
                }
                if(j>=k) res.add(i-k+1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "bcabbcaabbccacacbabccacaababcbbcdefghijklmnopqrrstuvwxyz";
        String[] words={"c","b","a","c","a","a","a","b","c"};
        List<Integer> substring = new Solution40().findSubstring(s, words);

    }
}
