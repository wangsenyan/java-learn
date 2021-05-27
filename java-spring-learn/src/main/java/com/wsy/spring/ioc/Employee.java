package com.wsy.spring.ioc;

public class Employee {
    private String name;
    private String gender;
    private Department dep;
    public void setName(String name) {
        this.name = name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setDep(Department dep) {
        this.dep = dep;
    }
    public Department getDep() {
        return dep;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dep=" + dep +
                '}';
    }

    public void add(){
        System.out.println("甄姬儿烦");
        System.out.println(toString());
        System.out.println(dep.toString());
    }
}
class Department{
    private String dname;
    public void setDname(String dname) {
        this.dname = dname;
    }
    public String toString() {
        return "Department{" +
                "dname='" + dname + '\'' +
                '}';
    }
}