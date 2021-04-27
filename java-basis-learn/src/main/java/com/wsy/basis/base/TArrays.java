package com.wsy.basis.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TArrays {
    /**
     * 1. 基本类型的数组
     * 2. 包装类型的数组
     * 3. 集合
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Integer[] arr2 = {1,2,3};
        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());

        int[] ints1 = Arrays.stream(arr2).mapToInt(Integer::intValue).toArray();
        int[] ints = collect.stream().mapToInt(Integer::intValue).toArray();


        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();

            iterator.remove();
        }

    }


}
