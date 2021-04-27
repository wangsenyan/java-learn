package com.wsy.basis.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Array {

    public static Class<?> getActualType(List<String> o,int index) {
        Type clazz = o.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType)clazz;
        return (Class<?>) pt.getActualTypeArguments()[index];
    }
    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("1");
        System.out.println(getActualType(arr, 0));
    }
}
