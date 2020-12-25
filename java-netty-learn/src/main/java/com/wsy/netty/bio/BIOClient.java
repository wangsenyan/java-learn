package com.wsy.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * BIO编程问题分析
 * 1、每个请求都需要创建独立的线程,与对应的客户端进行数据Read,业务处理,数据Write
 * 2. 当并发数较大时,需要创建大量的线程来处理连接,系统资源占用较大
 * 3. 连接建立后,如果当前线程暂时没有数据可读,则线程就阻塞在Read操作上,造成线程资源浪费
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        System.out.println("服务器启动了");
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3,
                6,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory()
        );
        while (true){
            final Socket socket = server.accept();
            System.out.println("连接到一个客户端");
            threadPool.execute(()->{
                handler(socket);
            });

        }
    }
    public static void  handler(Socket socket){
        try {
            System.out.println(Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if(read!=-1){
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
