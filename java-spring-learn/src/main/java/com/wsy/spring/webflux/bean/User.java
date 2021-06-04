package com.wsy.spring.webflux.bean;

public class User {
    private String name;
    private String author;

    public User() {
    }

    public User(String name, String author) {
        this.name = name;
        this.author = author;
    }

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
