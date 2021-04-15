package com.wsy.algorithm.niuke;

import java.util.*;

class Solution87 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //how to 用图解法
        HashSet<String> set = new HashSet<>();
        for(List<String> ls : equations){
            set.add(ls.get(0));
            set.add(ls.get(1));
        }
        int n = set.size();
        HashMap<String,Integer> hash = new HashMap<>();
        int t = 0;
        for (String s : set) {
            hash.put(s,t++);
        }
        double[][] var = new double[n][n];
        for (int i = 0; i < equations.size(); i++) {
            List<String> ls = equations.get(i);
            String k = ls.get(0);
            String v = ls.get(1);
            Integer r = hash.get(k);
            Integer c = hash.get(v);
            var[r][r] = var[c][c] = 1;
            var[r][c] = values[i];
            var[c][r] = 1/values[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(i==j || var[i][j]!=0) continue;
                    if(var[i][k]!=0 && var[k][j]!=0) {
                        var[i][j] = var[i][k] * var[k][j];
                        var[j][i] = 1/var[i][j];
                    }
                }
            }
        }
        //dp[][] [a,b,c,d] [a,b,c,d]
        int ql = queries.size();
        double[] ans = new double[ql];
        for (int i = 0; i < ql; i++) {
            List<String> qy = queries.get(i);
            String k = qy.get(0);
            String v = qy.get(1);
            Integer r = hash.get(k);
            Integer c = hash.get(v);
            if(r == null || c==null || var[r][c]==0.0)
                ans[i] = -1.0;
            else
                ans[i] = var[r][c];
        }
        return ans;
    }
    //深度遍历
    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(Arrays.asList("a","b"),Arrays.asList("b","c"),Arrays.asList("bc","cd"));
        double[] values = new double[]{3.4,1.4,2.3};
        List<List<String>> queries = Arrays.asList(Arrays.asList("b","a"),Arrays.asList("a","f"),Arrays.asList("f","f"),Arrays.asList("a","a"));
        double[] doubles = new Solution87().calcEquation(equations, values, queries);
        System.out.println(doubles);
    }
}