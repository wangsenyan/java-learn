package com.wsy.algorithm.niuke;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步编排:CompletableFuture
 *  1. runAsync
 *  2. supplyAsync().whenComplete((r,e)->{}).exceptionally(throwable -> {})
 *     thenRunA
 *
 */
public class ThreadTest01 {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args)throws Exception {
//        CompletableFuture.runAsync(()->{
//            System.out.println("当前线程:" + Thread.currentThread().getId());
//        }, executor);
//        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程:" + Thread.currentThread().getId());
//            return 5;
//        }, executor).whenComplete((r,e)->{
//            //虽然能得到异常信息,但不能修改返回数据
//            System.out.println("结果是"+ r);
//        }).exceptionally(throwable -> {
//            //感知异常同时返回默认值
//            return 10;
//        }).handle((e,r)->{
//            return 0;//修改了结果
//        });
//        System.out.println(cf.get());

//        CompletableFuture.supplyAsync(()->{
//            return 10;
//        },executor).thenAcceptAsync((r)->{
//            //既能感知上一步结果还能修改返回值
//            System.out.println(r);
//        },executor);

        CompletableFuture f1= CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        },executor);
        CompletableFuture f2= CompletableFuture.supplyAsync(()->{
            return 15;
        },executor);
        CompletableFuture f3= CompletableFuture.supplyAsync(()->{
            return 20;
        },executor);
//        f2.thenAcceptBothAsync(f1,(a,b)->{
//            System.out.println(a + " " +b);
//        },executor);

//        CompletableFuture f =  f1.thenCombineAsync(f2,(a,b)->{
//            return a + ": " + b + "haha";
//        },executor);
//        System.out.println(f.get());
//
//        f1.runAfterEitherAsync(f2,()->{
//            System.out.println("f结束");
//        },executor);
//        f1.acceptEitherAsync(f2,(r)->{
//            System.out.println("f结束"+ r);
//        },executor);
//        f1.applyToEitherAsync(f2,r->{
//            System.out.println("任务三开始");
//          return  r;
//        },executor);

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(f1, f2, f2);
        voidCompletableFuture.get();
        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(f1, f2, f3);
        System.out.println(objectCompletableFuture.get());
    }
}
