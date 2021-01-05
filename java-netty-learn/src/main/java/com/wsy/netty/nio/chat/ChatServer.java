package com.wsy.netty.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class ChatServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final int port = 7777;
    public ChatServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void listen(){
        try {
            while (true){
                System.out.println("等待......");
                int count = selector.select();
                if(count>0){
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()){
                        SelectionKey key = keyIterator.next();
                        if(key.isAcceptable()){
                            SocketChannel sc = serverSocketChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector,SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + "上线");
                        }
                        if(key.isReadable()){
                            //处理读
                            readData(key);
                        }
                        keyIterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }
    public void readData(SelectionKey key){
        //取得key对应的socketChannel
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if(count>0){
                String msg = new String(buffer.array());
                System.out.println("from 客户端: "+ msg);
                //转发消息,排除自己
                sendMsg(msg,channel);
            }
        }catch (IOException e){
            try {
                System.out.println(channel.getRemoteAddress() + "离线");
                channel.close();//离线后必须关闭相关通道,否则出现错误
                //java.net.SocketException: Connection reset
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    public void  sendMsg(String msg,SocketChannel self){
        System.out.println("转发消息中......");
        for(SelectionKey key: selector.keys()){
            //通过key获取相应的socketChannel,有可能是socketSocketChannel
            Channel targetChannel  = key.channel();
            if(targetChannel instanceof SocketChannel &&
                    targetChannel!=self){
                SocketChannel targetSocketChannel =(SocketChannel)targetChannel;
                try {
                    //将msg存储到buffer
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    targetSocketChannel.write(buffer);

                }catch (IOException e){
                    try {
                        System.out.println(targetSocketChannel.getRemoteAddress() + "离线了");
                        key.channel();//取消注册
                        targetSocketChannel.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //创建服务器对象
        ChatServer chatServer = new ChatServer();
        chatServer.listen();
    }
}
