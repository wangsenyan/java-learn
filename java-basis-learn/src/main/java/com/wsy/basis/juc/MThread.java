package com.wsy.basis.juc;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * [6] Monitor Ctrl-Break  //中断线程,win idea
 * [5] Attach Listener     //添加事件
 * [4] Signal Dispatcher   //分发处理给JVM信号的线程
 * [3] Finalizer           //调用对象finalize方法的线程
 * [2] Reference Handler   //清除reference线程
 * [1] main                //main线程,程序入口
 */
public class MThread {
    public static void main(String[] args) {
        MThread mThread = new MThread();
        mThread.threadList();
    }
    public void threadList(){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.toString());
            //System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
