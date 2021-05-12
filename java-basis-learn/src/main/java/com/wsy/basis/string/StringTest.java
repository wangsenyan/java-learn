package com.wsy.basis.string;

import java.util.Random;

public class StringTest {
    public static void main(String[] args) {
        StringTest stringTest = new StringTest();
        stringTest.intern();
    }
    public void fina(){
        //        String a = "hello2";
//        String b = "hello";
//        String c = b + 2;
//        System.out.println((a == c));

        String d = "hello2";
        final String e = "hello";
        String f = e + 2;
        System.out.println((d == f));
    }
    public void intern(){
        final int MAX = 1000 * 1000;
        final String[] arr = new String[MAX];
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 *10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            //arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }
        System.out.println((System.currentTimeMillis() - t) + "ms" );
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
    }
}
