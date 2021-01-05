package com.wsy.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 客户端
 */
public class SelectorClient {
    public static void main(String[] args)  throws Exception {
        //得到一个客户端网络通道
        SocketChannel client = SocketChannel.open();
        client.configureBlocking(false); //设置非阻塞
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",7001);
        if(!client.connect(inetSocketAddress)){
            //client.finishConnect() 表示被打断
            while (!client.finishConnect()){
                System.out.println("连接不上,客户端不会阻塞,做其他事儿");
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            ByteBuffer byteBuffer = ByteBuffer.wrap(scanner.nextLine().getBytes());
            client.write(byteBuffer);
        }
//        scanner.close();
        client.close();
//        //连接成功,发送数据
//        String str = "hello,world";
//        //wraps a byte array into a buffer
//        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
//        //发送数据,将buffer写入channel
//        client.write(byteBuffer);
//        //断开连接会出现 connect reset
//        client.close();
//        //防止connect reset,及时 client.close();
//        System.in.read();
    }
}
