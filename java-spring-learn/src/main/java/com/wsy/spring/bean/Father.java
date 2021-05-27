package com.wsy.spring.bean;

import java.util.List;

public class Father {
    public List<String> child;
    public void setChild(List<String> child) {
        this.child = child;
    }
    @Override
    public String toString() {
        return "Father{" +
                "child=" + child +
                '}';
    }
}
