package com.wsy.basis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE,ElementType.METHOD,
        ElementType.CONSTRUCTOR,ElementType.ANNOTATION_TYPE,
        ElementType.PACKAGE,ElementType.FIELD,
        ElementType.LOCAL_VARIABLE})
@interface Simple {
    String value() default "-default-";
}

class SimpleTest {
    @Simple
    int i;
    @Simple
    public SimpleTest(){}
    @Simple
    public void foo(){
        System.out.println("SimpleTest.foo()");
    }
    @Simple
    public void bar(String s,int i,float f){
        System.out.println("SimpleTest bar()");
    }
    @Simple
    public static void main(String[] args) {
        @Simple
        SimpleTest st=new SimpleTest();
        st.foo();
    }
}
