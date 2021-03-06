package com.wsy.algorithm.niuke;

import java.util.HashMap;

public class Solution47 {
    public String minWindow(String s, String t) {
        //比特位
        int sl = s.length();
        int tl = t.length();
        HashMap<Character,Integer> hash = new HashMap<>(); //保存最新的字符出现的位置
        long sub = 0;
        for(int i=0;i<tl;i++){
            sub |= 1<<(t.charAt(i)-'a');//sub记录t的比特位特征
        }
        int[] range = new int[]{-1,-1};
        long str = 0 ;
        int j = 0, min =Integer.MAX_VALUE;//j保存包含t出现的第一个字符
        for(int i=0;i<sl;i++){
            char c = s.charAt(i);
            str |= 1<< (c-'a');//对s求特征
            if((sub & 1 << (c - 'a'))>0){//如果这个位置在sub中,记录到hash表中,第一个位置如果重复出现呢?
                if(range[0]==-1) {
                    range[0] = i;//range[0]记录范围第一次出现的位置
                    j = i;
                }
                hash.put(c,i);//出现,就加入hash列表,char,index
            }
            if((str&sub)==sub){//如果当前范围已经包含,更新range
                int l = i - range[0];//范围的大小
                if(min<l) {
                    range[1]=i;
                }
                //range[1]=i;
                str^=(s.charAt(j));//str删除最近出现的字符
                j = hash.get(s.charAt(j))+1;
            }
        }
        return s.substring(range[0],range[1]);
    }
}
