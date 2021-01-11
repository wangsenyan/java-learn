package com.wsy.netty.nio.zerocopy;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * transferTo 零拷贝从操作系统看,kernel buffer -> protocol engine
 * NIO 是Reactor
 *     处理断连重连,网络闪断，半包读写，失败缓存，网络拥塞，异常流
 *     Epoll Bug,导致Selector空轮询,CPU 100%
 * AIO 是ProActor,异步通道,适合连接较多且连接时间较长的请求
 *
 */
public class NIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",7001));

        String fileName = "F:\\seata-1.4.0.zip";
        //hard drive->kernel buffer
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime =  System.currentTimeMillis();
        //linux下transform放开可以完成传输
        //windows下一次调用transferTo只能发送8M数据
        long pos = 0;
        long sent;
        do{
            //kernel buffer -> protocol engine
            //win下目前是mapper
            sent = fileChannel.transferTo(pos, fileChannel.size(), socketChannel);
            pos +=sent;
        }while (sent <=0 );
        System.out.println("发送总字节数 = "  + pos + "耗时=" + (System.currentTimeMillis() -startTime));
        fileChannel.close();

        socketChannel.close();
    }
}
