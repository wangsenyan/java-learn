package com.wsy.basis.juc;

public class _ThreadLocal {
    private static final int HASH_INCREMENT = 0x61c88647;
    public static void main(String[] args) {
        _ThreadLocal threadLocal = new _ThreadLocal();
        threadLocal.hashcode();
    }
    public void hashcode(){
        int hashcode = 0;
        for (int i = 0; i < 16; i++) {
            hashcode = i * HASH_INCREMENT + HASH_INCREMENT;
            int bucket = hashcode & 15;
            System.out.println(bucket);
        }
    }
}
