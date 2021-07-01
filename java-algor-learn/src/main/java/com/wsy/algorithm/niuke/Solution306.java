package com.wsy.algorithm.niuke;

public class Solution306 {
    int n;
    char[] ch ;
    public boolean isAdditiveNumber(String num) {
        //是否是斐波那契数列?只要确定前两位即可
        //长度最多为1/3长度 199100
        this.n = num.length();
        this.ch = num.toCharArray();
        //确定第一个和第二个数即可,第二个长度>=第三个数长度
        int m = n/2;
        //第一个长度为0~m
        for (int i = 1; i <= m; i++) {
            //第二个长度从i~m
            for (int j = 1; j <= m; j++) {
                if(check(0,i-1,i,i+j-1,1))
                    return true;
            }
        }
        return false;
    }
    private boolean check(int s1,int e1,int s2,int e2,int time){
        int ee = e2;
        //判断从[s1,e1] + [s2,e2] 是否在后续重复
        //字符串相加
        if( (s1!=e1 && ch[s1]=='0') ||
                (s2!=e2 && ch[s2]=='0')) return false;
        if(e2 > n-1) return false;
        if(e2 == n-1 && time > 1) return true;
        char[] sh = new char[Math.max(e1-s1,e2-s2) +2];
        int s = 0;
        int i = 0;
        //sh[k~0] 地位到高位对应ch[e2+1~e2+k+1]
        while (s1 <= e1 || s2 <= e2 || s!=0){
            int a1 = s1 <= e1 ? ch[e1--]-'0' : 0;
            int a2 = s2 <= e2 ? ch[e2--]-'0' : 0;
            sh[i++] = (char)((a1 + a2 + s)%10 + '0');
            s = (a1 + a2 + s) / 10;
        }
        int mm = i-1;
        for (i = mm; i >=0 ; i--) {
            int k = ee + 1 + mm - i;
            if( k > n -1) return false;
            if(sh[i] != ch[k])
                return false;
        }
        return check(s2,ee,ee+1,ee + mm +1,time+1);
    }

    public static void main(String[] args) {
        String[] nums = {"10","101","0000","112358","199100199"};
        for (String num : nums) {
            boolean additiveNumber = new Solution306().isAdditiveNumber(num);
            System.out.println(additiveNumber);
        }
    }
}
