package com.wsy.spring.ioc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * list
 * map
 * set
 */
public class Student {
    private String[] array;
    private List<String> list;
    private Map<String,String> map;
    private Set<String> set;
    private List<Course> courses;
    public void setArray(String[] array) {
        this.array = array;
    }
    public void setList(List<String> list) {
        this.list = list;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    public void setSet(Set<String> set) {
        this.set = set;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void test(){
        System.out.println(Arrays.toString(array));
        System.out.println(list);
        System.out.println(map);
        System.out.println(set);
        System.out.println(courses);
    }
}
class Course{
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}