package com.wsy.algorithm.niuke;

class Solution113 {
    private String s1;
    private String s2;
    int[][][] seen;
    public boolean isScramble(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;

        int n = s1.length();
        this.seen = new int[n][n][n];//0表示未遍历,1表示成功,2表示失败

        if(n==1) return s1.equals(s2);
        //随机从位置i出拆分
        //子序列是先拆分再合并
        boolean[][][] valid = new boolean[n][n][n+1];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if(s1.substring(i,i+1).equals(s2.substring(j,j+1)))
                    valid[i][j][1]= true;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int s = Math.min(n-i,n-j);//len的范围String
                for (int l = 0; l < s; l++) {//s1 [i,i+l),s2 [j,j+l)
                    for (int k = 0; k <= l; k++) {
                        if(valid[i][j][k] && valid[i+k][j+k][l-k]){
                            valid[i][j][l] = true;
                            break;
                        }
                        if(valid[i][j+l-k][k] && valid[i+k][j][l-k]){
                            valid[i][j][l] = true;
                            break;
                        }
                        //s1 [i,i+k) [i+k,i+l) s2 [j,j+k) [j+k,j+l)
                        //s2 [i,i+k) [i+k,i+l) s2 [j+l-k,j+l) [j,j+l-k)
                    }
                }
            }
        }
        return valid[0][0][n];
        //return dfs(0,n,0,n);
    }
    public boolean dfs(int st1,int ed1,int st2,int ed2){
        if(seen[st1][st2][ed1-st1-1]!=0){
            return seen[st1][st2][ed1-st1-1]==1;
        }
        String ss1 = s1.substring(st1, ed1);
        String ss2 = s2.substring(st2, ed2);
        if(ss1.equals(ss2)) {
            seen[st1][st2][ed1-st1-1]=1;
            return true;
        }
        boolean tag = false;

        for (int i = 1; i < ed1 - st1; i++) {
            tag =  ((dfs(st1,st1 + i,st2,st2 + i)
                    && dfs(st1 + i ,ed1,st2 + i ,ed2))
                    || (dfs(st1,st1 + i,ed2 -i,ed2)
                    && dfs(st1 + i ,ed1 , st2, ed2 - i)));
            if(tag) {
                seen[st1][st2][ed1-st1-1]=1;
                return true;
            }
        }
        seen[st1][st2][ed1-st1-1]=2;
        return false;
    }

    public static void main(String[] args) {
        String a = "rgeat";
        String b = "great";
        boolean scramble = new Solution113().isScramble(a, b);

    }
}