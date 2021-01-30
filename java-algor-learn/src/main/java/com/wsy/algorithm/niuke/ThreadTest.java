package com.wsy.algorithm.niuke;

import java.util.concurrent.*;

/**
 * 异步编排 CompletableFuture
 */
public class ThreadTest {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
        new Thread(futureTask).start();
        Integer integer = futureTask.get();//等待整个线程执行完成,获取返回结果
        System.out.println(integer);

        Executors.newFixedThreadPool(10);
        Executors.newScheduledThreadPool(10);
        new ThreadPoolExecutor(
                8,
                8,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(16),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy() //同步的执行
                );

    }
    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return 1;
        }
    }
}
