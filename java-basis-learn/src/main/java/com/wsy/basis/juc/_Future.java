package com.wsy.basis.juc;

import java.util.concurrent.*;

/**
 * 快速折叠代码块 ctrl _ shift _ -
 * run 无返回
 * supply 有返回
 *
 * whenComplete 完成,用同一个线程就行
 * whenCompleteSync,方法完成(成功、异常)后的处理
 *
 * handler 修改返回
 *
 * 线程串行化
 * then + [Apply,Accept,Run] + [Async]
 *    Async:开启新的线程
 *
 * Apply: 接收结果,返回结果
 * Accept: 接收结果,不返回结果
 * Run: 不接收结果,不返回结果
 *
 * 任务组合
 * thenCombine 获取两个任务结果并返回自己的结果
 * runAfterBoth 不获取也不返回
 * thenAcceptBoth获取两个任务结果不返回自己结果
 * thenAcceptBothAsync获取两个任务结果不返回自己结果
 *
 * 一个就行
 * runAfterEither 不获取也不返回
 * acceptEither 获取但不返回
 * applyToEither 感知结果并返回结果
 *
 * 多个组合
 * allOf
 *
 * 任何一个
 * anyOf
 */
public class _Future {
    private ThreadPoolExecutor executor;
    public _Future(){
        executor = new ThreadPoolExecutor(8,
                16,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    public void taskRun(){
      CompletableFuture.runAsync(()->{
          System.out.println("当前线程: " + Thread.currentThread().getId());
          int i = 10/2;
          System.out.println("运行结果： " + i);
      },executor);
    }
    public void taskSupply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程: " + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果： " + i);
            return i;
        }, executor).whenComplete((result,exception)->{
            //t result cb(r,e)
            //虽然能得到异常信息,但没法修改返回数据
            System.out.println("异步任务成功完成了.....结果是 " + result
                    + "异常是" + exception );
        }).handle((result,exception)->{
            if(result!=null)
                return result * 2;
            if(exception!=null)
                return 0;
            return 0;
        });
        Integer integer = future.get();
        System.out.println(integer);
    }
    public void testSer(){
        CompletableFuture<Object> future1 = CompletableFuture.supplyAsync(() -> {
            return "task1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "task2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "task3";
        });
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future1, future2, future3);
        try {
            Object o = voidCompletableFuture.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    public void combine(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "task1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "task2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "task3";
        });
        future1.thenCombine(future2,(r1,r2)->{
            System.out.println(r1 + " " + r2);
            return 0;
        });
        future1.thenAcceptBoth(future3,(r1,r2)->{
            System.out.println(r1 + " " + r2);
        });
    }
    public void testEther(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "task1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "task2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "task3";
        });
        future1.applyToEither(future2,(r)->{
            return r;
        });
    }
    public void testAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1");
            return "task1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2");
            return "task2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3");
            return "task3";
        });
        CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2, future3);
        all.get();
        System.out.println("碎尼玛逼");
    }
    public void testAnyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1");
            return "task1";
        },executor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task2");
            return "task2";
        },executor);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task3");
            return "task3";
        },executor);
        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2, future3);
        System.out.println(future.get());
    }
    public static void main(String[] args) throws Exception {
        _Future future = new _Future();
        //future.taskRun();
        //future.testSer();
        future.testAnyOf();
    }
}
