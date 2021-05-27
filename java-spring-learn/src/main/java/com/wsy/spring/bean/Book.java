package com.wsy.spring.bean;

public class Book {
    private String name;
    private String author;
    private String country;
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
