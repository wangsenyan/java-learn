package com.wsy.algorithm.niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution27 {
    public String findMaxArea(int av,int[] fails){
        int max = 0;
        int len = fails.length;
        double dp[][] = new double[len][len];
        for (int i = 0; i < len; i++) {
            //包含i的最长时间段
            for (int j = i; j < len; j++) {
                if(i==j) dp[i][j] = fails[i];
                else{
                    dp[i][j] = (dp[i][j-1] * (j-i) + fails[j])/(j-i+1);
                    max = dp[i][j]<=(double) av ? (j-i+1) : max;
                }
            }
        }
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if((j-i+1)==max && dp[i][j]<=av) {
                    s.append(i).append("-").append(j).append(" ");
                }

            }
        }
        String s1 = s.toString();
        return !s1.isEmpty() && s1.charAt(s1.length()-1) == ' ' ? s1.substring(0,s1.length()-1):s1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer n = Integer.valueOf(scanner.nextLine());
        String[] s = scanner.nextLine().split(" ");
        int[] list = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            list[i] = Integer.valueOf(s[i]);
        }
        System.out.println(new Solution27().findMaxArea(n, list));
    }
}
