package com.wsy.basis.Util;

import java.util.function.Predicate;

/**
 * 类似于 bool 类型的判断的接口，后面看看就明白了。
 */
public class _Predicate {
    public void testPredicate(){
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer > 5) return true;
                return false;
            }
        };
        Predicate<Integer> predicate1 = (i) -> i <5;
        boolean test = predicate.test(6);
        boolean test2 = predicate.and(predicate1).and(predicate1).test(6);
        System.out.println(test2);

        predicate = (t) -> t > 5;
        boolean test1 = predicate.test(4);
        System.out.println(test1);
    }

    public static void main(String[] args) {
        _Predicate predicate = new _Predicate();
        predicate.testPredicate();
    }
}
