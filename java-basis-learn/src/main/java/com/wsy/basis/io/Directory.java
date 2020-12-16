package com.wsy.basis.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * pattern = Pattern.compile(regex)
 * pattern.matcher(new File(name))
 * File f =  new File()
 *
 */
public final class Directory {
    public  static File[] local(File dir,final String regex){
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
               return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }
    public  static File[] local(String path,final String regex){
        return local(new File(path),regex);
    }
    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();
        public Iterator<File> iterator() {
            return files.iterator();
        }
        void addAll(TreeInfo other){
            files.addAll(other.files);
            dirs.addAll(other.files);
        }
        public String toString(){
            return "dirs: "+ dirs +
                    "\n\nfiles: "+files;
        }
    }
    public static TreeInfo walk(String start,String regex){
        return recurseDirs(new File(start),regex);
    }
    public static TreeInfo walk(File start,String regex){
        return recurseDirs(start,regex);
    }
    public static TreeInfo walk(File start){
        return recurseDirs(start,".*");
    }
    public static TreeInfo walk(String start){
        return recurseDirs(new File(start),".*");
    }
    static TreeInfo recurseDirs(File startDir,String regex){
       TreeInfo result = new TreeInfo();
       for(File item: startDir.listFiles()){
           if(item.isDirectory()){
               result.dirs.add(item);
               result.addAll(recurseDirs(item,regex));
           }else
               if(item.getName().matches(regex))
                   result.files.add(item);
       }
       return result;
    }

    public static void main(String[] args) {
        if(args.length == 0)
            System.out.println(walk("."));
        else
            for(String arg:args)
                System.out.println(walk(arg));
    }
}

class InterfaceOfFile {
    public static void main(String[] args) throws IOException {
        File f = new File("a_test");
        System.out.println(f.getAbsoluteFile()); ;
        System.out.println(f.canRead());
        System.out.println(f.canWrite());
        System.out.println(f.getName());
        System.out.println(f.getParent());
        System.out.println(f.getPath());
        System.out.println(f.length());
        System.out.println(f.lastModified());
        System.out.println(f.isDirectory());
        System.out.println(f.isFile());
        System.out.println(f.exists());
        System.out.println(f.list());
        f.createNewFile();
        f.mkdirs();//优先createNewFile
        //f.delete();
    }
}
