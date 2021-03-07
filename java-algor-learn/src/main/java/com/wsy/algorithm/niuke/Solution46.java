package com.wsy.algorithm.niuke;
class Solution46 {
    private String s;
    public boolean isNumber(String s) {
        this.s = s;
        int len = s.length();
        //小数 + (e) + 整数
        //(+/-) + (数字) + '.' + (数字)  == 小数
        //(+/-) + 数字
        //以e分割, e后面必须是整数
        //e前面可以是小数或整数
        //如果是小数,用'.'分割
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='e' || s.charAt(i)=='E')
                return (isDecimal(0,i-1) || isInteger(0,i-1)) && isInteger(i+1,len-1);
        }
        return  isDecimal(0,len-1) || isInteger(0,len-1);
    }
    private boolean isInteger(int st,int ed){
        if(st > ed) return false;
        char first = s.charAt(st);
        if(first=='+' || first=='-'){
            return isDigital(st+1,ed);
        }else{
            return !isDigital(st,ed);
        }
    }
    private boolean isDecimal(int st,int ed){
        if(st>ed) return false;
        char first = s.charAt(st);
        if((first=='+' || first=='-') && st == ed) return false;
        for(int i= st;i<=ed;i++){
            if(s.charAt(i)=='.'){
                if(first=='+' || first == '-')
                    return !isDigital(st+1,i-1) && !isDigital(i+1,ed);
                else
                    return !isDigital(st,i-1) && !isDigital(i+1,ed);
            }
        }
        return false;

    }
    private boolean isDigital(int st,int ed){
        if(st>ed) return false;
        for(int i=st;i<=ed;i++)
        {
            if(s.charAt(i) < '0' || s.charAt(i) >'9') return false;
        }
        return true;
    }
}
