package com.wsy.basis.base;

import org.openjdk.jol.info.ClassLayout;

public class ObjectHeader {
    static int p = 1;
    static byte b = 2;
    static char c = 'c';
    long l = 3;
    static {
        double b = 1;
    }
    public static void main(String[] args) {
        String SPLITE_STR = "=========";
        ObjectHeader header = new ObjectHeader();
        System.out.println(SPLITE_STR);
        System.out.println(ClassLayout.parseInstance(header).toPrintable());
        System.out.println(SPLITE_STR);
    }
}
