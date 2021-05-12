package com.wsy.basis.Util;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 转换接口,将输入数据转换成另一种形式的输出数据
 * 使用Lambda的依据是必须有相应的函数接口(内部有一个抽象方法的接口)
 * Lambda表达式另一个依据是类型推断机制
 * @FunctionalInterface 检查接口是否符合函数接口规范
 */
public class _Function {
    public void testFunction(){
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        Stream<String> stream = Stream.of("a", "bb", "ccc", "dddd", "eeeee", "ffffff");
        Stream<Integer> stream1 = stream.map(function);
        //
        stream1.forEach(System.out::println);
    }

    public static void main(String[] args) {
        _Function function = new _Function();
        function.testFunction();
    }
}
