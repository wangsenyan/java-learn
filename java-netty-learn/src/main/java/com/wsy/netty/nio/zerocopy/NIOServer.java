package com.wsy.netty.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定地址
        ssc.socket().bind(new InetSocketAddress(7001));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel = ssc.accept();//每次能接收多少数据?
            System.out.println("新的请求"+socketChannel.getRemoteAddress());

            long total=0;
            int readCount;
            try {
                while ((readCount = socketChannel.read(byteBuffer))>0){
                    total+=readCount;
                    byteBuffer.rewind();//设置position=0,mark unset
                }
                System.out.println("获取数据: "+ total);
            }catch (Exception e){
                socketChannel.close();
            }finally {
                //如果客户端socketChannel没有正常关闭,这句就会打印
                //因为出现connect reset
                System.out.println("获取数据: "+ total);
            }
        }
    }
}
