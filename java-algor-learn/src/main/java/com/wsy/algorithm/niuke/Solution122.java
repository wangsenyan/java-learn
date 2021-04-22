package com.wsy.algorithm.niuke;

public class Solution122 {
    private String _s;
    private int ans = 0;
    private boolean valid;
    public int numDecodings(String s) {
        int n = s.length();
        int[] res = new int[n+1];
        if(s.charAt(0) == '0')
            return 0;
        res[0] = 1;
        res[1] = 1;
        for (int i = 1; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i-1);
            if(c1 == '0'){
                if(c2 == '0' || c2 > '2')
                    return 0;
                else
                    res[i+1] = res[i-1];
            }else if(c2 == '1' || c2 == '2' && c1 < '7'){
                res[i+1] = res[i] + res[i-1];
            }else{
                res[i+1] = res[i];
            }
        }
        return res[n];
    }
    private void helper(int idx){
       if(idx == _s.length()){
           ans++;
           return;
       }
       if(_s.charAt(idx) == '0')
           return;
       helper(idx + 1);
       if(idx < _s.length() - 1 && !(_s.charAt(idx) > '2'  && _s.charAt(idx+1)>'6'))
           helper(idx + 2);
    }

    public static void main(String[] args) {
        int i = new Solution122().numDecodings("11101");
    }
}
