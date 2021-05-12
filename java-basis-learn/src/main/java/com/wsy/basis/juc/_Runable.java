package com.wsy.basis.juc;

import java.util.Date;
import java.util.concurrent.*;

/**
 * execute()提交不需要返回值的任务,无法判断任务是否被线程池执行成功与否
 * submit() 用于提交需要返回值的任务,返回Future类型的对象,get()阻塞,
 *          get(long timeout,TimeUnit unit)
 */
public class _Runable {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 50;
    private static final Long KEEP_ALIVE_TIME = 1L;
    public void excutor(){
        ExecutorService service1 = Executors.newFixedThreadPool(8);
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        ExecutorService service3 = Executors.newCachedThreadPool();

        new ThreadPoolExecutor(8,
                16,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                 );
    }
    class MyRunable implements Runnable{
        private String command;
        public MyRunable(String s){
            this.command = s;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Start time=" + new Date());
            processCommand();
            System.out.println(Thread.currentThread().getName() + " End time= " + new Date());
        }

        private void processCommand() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    public void test(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 100; i++) {
            Runnable worker = new MyRunable("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()){

        }
        System.out.println("Finished all threads");
    }

    public static void main(String[] args) {
        _Runable runable = new _Runable();
        runable.test();
    }
}
