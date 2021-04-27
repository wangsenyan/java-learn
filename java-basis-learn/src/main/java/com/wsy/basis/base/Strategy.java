package com.wsy.basis.base;

public enum Strategy {
    ADD("+"){
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },
    SUB("-"){
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    };
    Strategy(String s){
       this.value = s;
    }
    private final String value;
    public abstract int exec(int a,int b);
}