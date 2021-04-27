package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.List;

public class Solution150 {
    public int numSteps1(String s) {

        //特例,1111,为 10000/2

        char[] cs = s.toCharArray();
        List<Character> css = new ArrayList<>();

        int m = 0;
        int n = cs.length;
        int i = n -1;
        while (i > 0){
            char c = cs[i];
            if(c=='0') {
                i--;
            }else{
                int k = i;
                while (k >=0  && cs[k] == '1'){
                    cs[k] = '0';
                    k--;
                }
                if(k == -1){
                    return m + i + 2;
                }else{
                    cs[k] = '1';
                }
            }
            m++;
        }
        return m;
    }
    public int numSteps(String s) {
        int zero = 0;
        int i = s.length() - 1;
        while(i >= 0 && s.charAt(i) != '1') i--;
        if(i == 0) return s.length() - 1;
        while(i > 0) if(s.charAt(i--) == '0') zero++;
        return zero + s.length() + 1;
    }

    public static void main(String[] args) {

        String s = "1101110000110101010110011010101010010101110010101010101110101010101110101";
        int i = new Solution150().numSteps(s);
        System.out.println(i);
    }
}
