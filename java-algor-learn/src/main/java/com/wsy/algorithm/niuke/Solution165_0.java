package com.wsy.algorithm.niuke;

public class Solution165_0 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version1.split("\\.");
        int i = 0 , j = 0;
        while(i < v1.length || j < v2.length){
            int n1 = i < v1.length ? Integer.valueOf(v1[i++]):0;
            int n2 = j < v2.length ? Integer.valueOf(v2[j++]):0;
            System.out.println(n1 + " " + v1.length + " " + n2 + " " + v2.length);
            if(n1!=n2) return n1 > n2 ? 1 : -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution165_0 solution165_0 = new Solution165_0();
        String v1 = "1.0";
        String v2 = "1.00.1";
        int i = solution165_0.compareVersion(v1, v2);
        System.out.println(i);
    }
}
