package com.wsy.basis.base;

import java.io.*;
import java.util.Scanner;

public class Finally {
    public int f(int v){
        //变量v保存在lv=1
        try {
            return v * v;//v*v保存在lv=2
        }finally {
            if(v == 2) v = 3;//修改了lv=1的值,依然返回lv=2
        }


    }
    public void scan(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("F:/a.txt"));
            while (scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(scanner!=null){
                scanner.close();
            }
        }
    }
    public void scan1(){
        //BufferedOutputStream有缓冲区,FileOutputStream没有
        try(BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
            int b;
            while ((b=bin.read())!=-1){
                bout.write(b);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Finally f = new Finally();
        System.out.println(f.f(2));
    }
}
