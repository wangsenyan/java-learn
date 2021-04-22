package com.wsy.algorithm.niuke;


import java.util.HashMap;

public class TextMap {
    public static void main(String[] args){
        HashMap<User, User> map = new HashMap<User, User>();
        for(int i = 1; i <= 20; i++){
            User user = new User();
            map.put(user , user);
            System.out.println("i =" + i);
        }
        HashMap<Integer,Integer> hash = new HashMap<>();
        for (int i = 0; i < 200; i++) {
            hash.put(i,i);
        }
    }
    static class User{
        //保证每个元素放入同一个索引,将hashcode设置为1
        @Override
        public int hashCode() {
            return 1;
        }
        //保证在同一个索引中,每个元素不会因为相同被覆盖,将equles设置为false
        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
