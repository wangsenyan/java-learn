package com.wsy.basis.base;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public byte count(byte b1, byte b2){
        int a=1,b=2;
        int c=(a+b>3?a++:++b);
        System.out.println(a + " " + b + " " + c);
        return (byte) (b1 / b2);

    }

    public static void main(String[] args) throws IOException {
        System.out.println("" + 'a' + 1 );
        Test1 test1 = new Test1();
        List<Integer> list = new ArrayList<>();
        FileOutputStream fos = new FileOutputStream("c:\\demo.txt");
        fos.write("abc".getBytes(StandardCharsets.UTF_8));
        fos.close();
        System.out.println(test1.count((byte) 1,(byte) 2));
    }
}
