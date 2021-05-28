package com.wsy.spring.autowire;

public class Emp {
    private Dep dep;
    public void setDep(Dep dep) {
        this.dep = dep;
    }
    public String toString() {
        return "Emp{" +
                "dep=" + dep +
                '}';
    }
}
