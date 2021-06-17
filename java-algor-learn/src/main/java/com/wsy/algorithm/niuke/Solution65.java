package com.wsy.algorithm.niuke;

/**
 * 有效数字（按顺序）可以分成以下几个部分：
 *
 * 1. 一个 小数 或者 整数
 * 2.（可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：
 *
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 *
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 */
public class Solution65 {
    public boolean isNumber(String s) {
        //1. 先用e|E拆开,e只能有一个?
        //2. e|E 前是小数或整数, e|E后面是整数
        //3. 按.拆开,.前后都是整数

        String[] sArr = s.split("[e|E]");
        int n = sArr.length;

        int len = s.length();
        for (int i = 0; i < len; i++) {
            if(s.charAt(i)=='e' || s.charAt(i)=='E'){
                return isFloat(s.substring(0,i)) && isN(s.substring(i+1));
            }
        }
        return isFloat(s);
    }
    //判断小数
    public boolean isFloat(String s){
        if(s==null || s.isEmpty())
            return false;
        char c = s.charAt(0);
        if(c=='+' || c=='-') s = s.substring(1);
        String[] sArr = s.split("\\.",-1);
        int n = sArr.length;
        if(n > 2 || (n==2 && sArr[0].isEmpty() && sArr[1].isEmpty()) || (n==1 && sArr[0].isEmpty()) )
            return false;
        for (String ss : sArr) {
            if(ss.isEmpty()) continue;
            if(!isDigital(ss))
                return false;
        }
        return true;
    }
    //判断整数
    public boolean isDigital(String s){
        if(s==null || s.isEmpty())
            return false;
        char[] cs = s.toCharArray();
        for (int i = 0 ; i < cs.length ; i++) {
            if(cs[i]>'9' || cs[i] < '0')
                return false;
        }
        return true;
    }
    //判断整数
    public boolean isN(String s){
        if(s==null || s.isEmpty()) return false;
        char c = s.charAt(0);
        if(c=='+' || c=='-') s = s.substring(1);
        return isDigital(s);
    }

    public static void main(String[] args) {
        String[] strs = {"+.1","2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"};
        String[] strf = {"+E3","abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"};
        Solution65 solution65 = new Solution65();
        for (String str : strf) {
            System.out.println(solution65.isNumber(str));
        }
    }
}
