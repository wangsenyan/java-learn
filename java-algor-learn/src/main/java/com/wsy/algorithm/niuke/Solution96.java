package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

public class Solution96 {
    private List<String> wordDict;
    private int wordLen;
    private List<String> ans;
    public List<String> wordBreak(String s, List<String> wordDict) {
        this.wordDict = wordDict;
        this.wordLen = wordDict.size();
        this.ans = new ArrayList<>();
        //字典需要遍历多种情况
        //字典树,最先匹配的
        if(s.length()<=0) return null;
        List<String> sb = new ArrayList<>();
        wordBreakHelper(s,sb);
        return ans;
    }
    private void wordBreakHelper(String  s,List<String> sb){
        if(s.isEmpty()){
            ans.add(String.join(" ",sb));
            return;
        }
        for (int i = 0; i < wordLen; i++) {
            String c = wordDict.get(i);
            if(s.startsWith(c)){
                sb.add(c);
                wordBreakHelper(s.substring(c.length()),sb);
                sb.remove(sb.size() -1);
            }
        }
    }
}
