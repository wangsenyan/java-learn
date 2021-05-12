package com.wsy.basis.Util;

import java.util.Arrays;
import java.util.List;

public class _Stream {
    List<String> strList;
    public _Stream(){
        strList = Arrays.asList("aaa","bbb","ccc","ddd","eee");
    }
    public void _filter(){
        strList.stream().filter((s)->s.startsWith("a"))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        _Stream stream = new _Stream();
        stream._filter();
    }
}
