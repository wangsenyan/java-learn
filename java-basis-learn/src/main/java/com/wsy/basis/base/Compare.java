package com.wsy.basis.base;

import java.util.*;
import java.util.stream.Collectors;

/**
 * - comparable - compareTo
 * 1. 实现了Comparable的对象都有compareTo方法
 * 2. 包装类型都实现了compareTo方法
 * comparator - compare
 */
public class Compare {
    public void compare(){
        int[] arr = {1,3,5,7,9,2,4,6,8};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

    }
    class A extends HashMap {

    }
    //cap 34444  1000011010001100
    //把首位的1扩展到全位数
    static final int tableSizeFor(int cap) {
        int n = cap - 1; // 1000011010001011
        n |= n >>> 1; //     100001101000101 = 1100011111001111
                      //    1100011111001111
        n |= n >>> 2; //      11000111110011 = 1111011111111111
                      //    1111011111111111
        n |= n >>> 4; //        111101111111 = 1111111111111111
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }
}
