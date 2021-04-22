package com.wsy.algorithm.niuke;

class Solution44 {
    private String[] s = new String[32];
    public Solution44(){
        s[0]="1";
        for(int i=1;i<32;i++){
            s[i]=gen(s[i-1]);
        }
    }
    private String gen(String s){
        int len = s.length();
        StringBuilder res = new StringBuilder();
        int i = 0,j = 0;
        while (i<len){
            char p = s.charAt(j);
            while (i<len && p==s.charAt(i))
                i++;
            res.append((i-j));
            res.append(p);
            j=i;
        }
        return res.toString();
    }
    public String countAndSay(int n) {
        return s[n-1];
    }

    public static void main(String[] args) {
        final String s = new Solution44().countAndSay(5);
    }
}