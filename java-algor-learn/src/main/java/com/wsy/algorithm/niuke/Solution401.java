package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

public class Solution401 {
    private List<List<Integer>> hour;
    private List<List<Integer>> minute;
    public Solution401(){
        hour = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            ArrayList<Integer> eh = new ArrayList<>();
            zh(0,4,0,i,0,eh);
            hour.add(eh);
        }
        minute = new ArrayList<>();
        for (int i = 0; i <=  6; i++) {
            ArrayList<Integer> em = new ArrayList<>();
            zh(0,6,0,i,0,em);
            minute.add(em);
        }
    }
    public List<String> readBinaryWatch1(int turnedOn) {
        String[][] h = new String[][]{{"0"},{"1","2","4","8"},{"3","5","9","6","10","12"},{"14","13","11","7"},{"15"}};//4个都亮无效
        String[][] m = new String[][]{{"0"},
                {"1","2","4","8","16","32"},
                {"3","5","9","17","33","6","10","18","34","12","20","36","24","40","48"},
                {"7","11","19","35","13","21","37","25","41","49","26","42","50","14","22","38","28","44","52","56"},
                {"60","58","54","46","30","57","53","45","29","51","43","27","39","23","15"},
                {"62","61","59","55","47","31"},
                {"63"}};
        //turnedOn拆分
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= turnedOn && i < 4; i++) {
            String[] hh = h[i];
            int j = turnedOn - i;
            if(j < 6){
                String[] mm = m[j];
                for (String hs : hh) {
                    if(Integer.parseInt(hs)<12){
                        for (String ms : mm) {
                            if(Integer.parseInt(ms)<60){
                                res.add(hs + ":" + (ms.length()<2?"0"+ms:ms));
                            }
                        }
                    }

                }
            }
        }
        return res;
    }

    public List<String> readBinaryWatch(int turnedOn){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if(count(i) + count(j)==turnedOn)
                    res.add(i + ":" + (j<10?"0"+j:j));
            }
        }
        return res;
    }
    public int count(int n){
        int r = 0;
        while (n > 0){
            r+=(n&1);
            n>>>=1;
        }
        return r;
    }

    /**
     * 二进制n位数中选择t位数的组合列表
     * @param i 当前位置
     * @param n 位数总数
     * @param c 已求位数
     * @param t 需要位数
     * @param e 已求位数和
     * @param r 符合列表
     */
    public void zh(int i,int n, int c,int t,int e,List<Integer> r){
       if(i==n || c==t) {
           if(c==t)
              r.add(e);
           return;
       }
       //包含当前的值
       zh(i+1,n,c+1,t,e+(1<<i),r);
       //不包含
       zh(i+1,n,c,t,e,r);
    }

    public static void main(String[] args) {
        List<String> strings = new Solution401().readBinaryWatch(10);
        System.out.println(strings);
    }
}
