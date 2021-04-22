package com.wsy.basis.base;

public class MString {
    public void newString(){
        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println(s1 == s2);
    }

    public static void main(String[] args) {
        MString mString = new MString();
        mString.newString();
    }
}
