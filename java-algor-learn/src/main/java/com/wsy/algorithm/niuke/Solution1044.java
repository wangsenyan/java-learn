package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution1044 {
    private int[] num;
    private int mod;
    private int len;
    private String s;
    public String longestDupSubstring(String s) {
        int n = s.length();
        this.mod =  (int) Math.pow(2,32);
        this.num = new int[n];
        this.len = n;
        this.s = s;
        for (int i = 0; i < n; i++) {
            this.num[i] = s.charAt(i) - 'a';
        }


        String tt = "";
        int l = 0, r = n;
        while (l <= r){
            int m = (l + r)/2;
            String t = exists(m);
            if(t!=null) {
                l = m + 1;
                if(t.length() > tt.length())
                    tt = t;
            }else
                r = m-1;
        }
        return tt;
    }
    public String exists(int m){
        //判断是否有m
        HashSet<Long> set = new HashSet<>();
        HashMap<Long, List<Integer>> seen = new HashMap<>();
        //记录每个数高次后的mod
        long ds = 0;//以31为底数
        long aL = 1;
        int q = 26;
        for (int i = 0; i < m; i++) {
            aL = (aL * q) % mod;
        }
        for (int i = 0; i < len - m + 1; i++) {
            if(i == 0){
                for (int j = 0; j < m; j++) {
                    ds = (ds * q + num[j]) % mod;
                }
            }else{
                ds = (ds * q - num[i - 1] * aL % mod + mod) % mod;
                ds = (ds + num[i + m - 1]) % mod;
            }
            if(seen.containsKey(ds)){

            }else{
                List<Integer> ts = new ArrayList<>();
                ts.add(i);
                seen.put(ds,ts);
            }
            if(set.contains(ds)){
                return  s.substring(i,i+m);
            }
            set.add(ds);
        }
        return null;
    }

    public static void main(String[] args) {
        String ss = "okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajvielokmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel";
        String s = new Solution1044().longestDupSubstring(ss);
        System.out.println(s);
    }
}
