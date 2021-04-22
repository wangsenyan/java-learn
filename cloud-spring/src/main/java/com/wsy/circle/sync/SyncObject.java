package com.wsy.circle.sync;

public class SyncObject {
    private static int j = 0;


    /**
     * 同步库数据，比较耗时，代码资源不涉及共享资源读写操作。
     */
    public void syncDbData() {
        System.out.println("db数据开始同步------------");
        try {
            //同步时间需要2秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("db数据开始同步完成------------");
    }

    //自增方法
    public synchronized void incr() {
        //start--临界区代码
        //同步库数据
        syncDbData();
        for (int i = 0; i < 10000; i++) {
            j++;
        }
        //end--临界区代码
    }

    //自增方法
    public void incrTwo() {
        //同步库数据
        syncDbData();
        synchronized (this) {
            //start--临界区代码
            for (int i = 0; i < 10000; i++) {
                j++;
            }
            //end--临界区代码
        }

    }
    public void incrThree(){
        syncDbData();
        synchronized (new Object()){
            for (int i = 0; i < 10000; i++) {
                j++;
            }
        }
    }
    public int getJ() {
        return j;
    }

    public static void main(String[] args) throws InterruptedException {
        SyncObject sync = new SyncObject();
        Thread t1 = new Thread(() -> sync.incr());
        Thread t2 = new Thread(() -> sync.incr());
        Thread t5 = new Thread(() -> sync.incrThree());
        t1.start();
        t2.start();
        t5.start();
        t1.join();
        t2.join();
        t5.join();
        System.out.println(sync.getJ());
       // System.out.println(ClassLayout.parseInstance(sync).toPrintable());


        //Thread t3 = new Thread(() -> sync.incrTwo());
        //Thread t4 = new Thread(() -> sync.incrTwo());
        //Thread t5 = new Thread(() -> sync.incrThree());
        //t3.start();
        //t4.start();
        //t5.start();
        //t3.join();
        //t4.join();
        //t5.join();
        //System.out.println(sync.getJ());
    }
}
