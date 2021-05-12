package com.wsy.basis.Util;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 消费者
 */
public class _Consumer {
    public void testConsumer(){
        Consumer<String> consumer = new Consumer<String>(){
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Stream<String> stream = Stream.of("aaa", "bbb", "ccc", "ddd", "eee");
        stream.forEach(consumer);
    }

    public static void main(String[] args) {
        _Consumer consumer = new _Consumer();
        consumer.testConsumer();
    }
}
