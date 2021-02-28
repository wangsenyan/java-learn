package com.wsy.algorithm.niuke;

class Solution38 {
    private String s;
    public int longestSubstring(String s, int k) {
        this.s = s;
        return recurve(0,s.length()-1,k);
    }
    public int recurve(int start,int end,int k){
        if(end - start + 1 < k ) return 0;
        int[] ch = new int[26];
        for(int i=start;i<=end;i++){
            ch[s.charAt(i)-'a']++;
        }
        int max = 0;
        for(int i=start,j=start;i<=end;i++){
            if(ch[s.charAt(i)-'a']< k ){
                return  Math.max(recurve(j,i-1,k),recurve(i+1,end,k));
            }
        }
        return end - start + 1;
    }

    public static void main(String[] args) {
        String s = "bbaaacbd";
        int k = 3;
        System.out.println(new Solution38().longestSubstring(s, k));
    }
}
