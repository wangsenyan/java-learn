package com.wsy.spring.bean;

public class User {
    private String name;
    private String author;
    public void add(){
        System.out.println("bean.user.add()");
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
