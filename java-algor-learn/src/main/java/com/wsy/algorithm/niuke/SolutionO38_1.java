package com.wsy.algorithm.niuke;
import java.util.LinkedList;
import java.util.List;

public class SolutionO38_1 {
    List<String> list;
    public String[] permutation(String s) {
        //交换算法
        char[] cs = s.toCharArray();
        this.list = new LinkedList<>();
        int n = s.length();
        dfs(0,n,cs);
        return list.toArray(new String[0]);

    }
    public void dfs(int i,int n,char[] cs){
        if(i==n){
            list.add(String.valueOf(cs));
            return;
        }
        for (int j = i; j < n; j++) {
            boolean flag = true;
            for (int k = i; k < j; k++) {
                if(cs[j]==cs[k]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                swap(cs,i,j);
                dfs(i+1,n,cs);
                swap(cs,i,j);
            }
        }
    }
    private void swap(char[] cs,int s,int d){
        char t = cs[s];
        cs[s] = cs[d];
        cs[d] = t;
    }
    public static void main(String[] args) {
        String s = "addde";
        String[] permutation = new SolutionO38_1().permutation(s);
        System.out.println(permutation);
    }
}
