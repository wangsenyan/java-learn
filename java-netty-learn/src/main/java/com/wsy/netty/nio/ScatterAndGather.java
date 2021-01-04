package com.wsy.netty.nio;

import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 注意，telnet 127.0.0.1 7000 后要 ctrl + ']'
 */
public class ScatterAndGather {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.socket().bind(inetSocketAddress);
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true){
            int byteRead = 0;
            while (byteRead < messageLength){
                long l = socketChannel.read(byteBuffers);
                byteRead +=l;
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers).stream().map(buffer->"position="+
                        buffer.position() + ",limit=" + buffer.limit()).forEach(System.out::println);
                Arrays.asList(byteBuffers).forEach(buffer->buffer.flip());
            }
            long byteWrite = 0;
            while (byteWrite < messageLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite+=l;
            }
            Arrays.asList(byteBuffers).forEach(buffer->{buffer.clear();});
            System.out.println("byteRead:="+ byteRead
                    + "byteWrite="+ byteWrite
                    + "messageLength=" + messageLength);
        }
    }
}
