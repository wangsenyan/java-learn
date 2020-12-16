package com.wsy.basis.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if(args.length == 0)
            list = path.list();
        else
            //回调-》策略模式
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String dirItem:list)
            System.out.println(dirItem);
    }
}
class DirFilter implements FilenameFilter{
    private Pattern pattern;
    public DirFilter(String regex){
        pattern = Pattern.compile(regex);
    }
    public boolean accept(File dir,String name){
        return pattern.matcher(name).matches();
    }
}
//匿名内部类
class DirList2 {
    public static FilenameFilter filter(final String regex){
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return  pattern.matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if(args.length == 0)
            list = path.list();
        else
            list = path.list(filter(args[0]));
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String dirItem:list)
            System.out.println(dirItem);
    }
}
//
class DirList3 {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if(args.length==0)
            list = path.list();
        else
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(args[0]);
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String dirItem:list)
            System.out.println(dirItem);
    }
}