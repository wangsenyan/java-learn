package com.wsy.basis.interview;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Person{
    private String name;
    public Person(String name) {
        this.name = name;
    }
}
public class TestTransferValue {
    public void changeValue1(int age){
        age = 30;
    }
    public void changeValue2(Person person){
        person.setName("xxx");
    }
    public void changeValue3(String str){
       // str="xxx";
        str = new String("xxx");
    }
    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age=10;
        test.changeValue1(age);
        System.out.println("age----"+age);//10
        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName---"+person.getName());//xxx
        String str = "abc";
        test.changeValue3(str);
        System.out.println("string----"+str);//abc
    }
}
