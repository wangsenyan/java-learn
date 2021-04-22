package com.wsy.basis;

public class CHM {
    public volatile User user;
    static class User{
        private int value;
        @Override
        public int hashCode() {
            return super.hashCode()%4;
        }
        public User(int value){
            this.value = value;
        }
    }
    public static void main(String[] args) {
        CHM chm = new CHM();
        User user = new User(1);
        chm.user = user;
    }
}
