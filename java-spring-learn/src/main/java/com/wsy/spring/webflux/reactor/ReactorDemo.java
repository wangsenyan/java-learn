package com.wsy.spring.webflux.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Reactor两个核心，Mono和Flux,实现Publisher
 *   1. Mono实现发布者,返回0或者1个元素
 *   2. Flux实现发布者,返回N个元素
 *   3. Flux和Mono都是数据流的发布者,都可以发出三种数据信息,元素值,错误信号,完成信号。
 *      错误信号和完成信号都代表终止信号,
 */
public class ReactorDemo {
    public static void main(String[] args) {
        Flux.just(1,2,3,4);
        Mono.just(1);
        Integer[] array = {1,2,3};
        Flux.fromArray(array);
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
    }
}
