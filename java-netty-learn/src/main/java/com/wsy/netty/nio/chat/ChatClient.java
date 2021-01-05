package com.wsy.netty.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 客户端
 * 1. 连接
 * 2. 发送
 * 3. 接收
 */
public class ChatClient {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public ChatClient(String host, int port, String username) throws IOException {
        this.host = host;
        this.port = port;
        this.username = username;
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(host,port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println(username + "is ok......");
    }
    public void sendMsg(String msg){
        msg = username + ":" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void readMsg(){
        int readChannel = 0;
        try {
            readChannel = selector.select();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(readChannel>0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey sk = iterator.next();
                if(sk.isReadable()){
                   SocketChannel sc = (SocketChannel) sk.channel();
                   ByteBuffer buffer = ByteBuffer.allocate(1024);
                   try {
                       sc.read(buffer);
                   }catch (IOException e){
                       try {
                           //不从selector中删除,会报connect reset
                           System.out.println("服务器异常");
                           sc.close();
                       } catch (IOException ioe) {
                           ioe.printStackTrace();
                       }
                   }
                   //把读到的缓存区的数据转换成字符串
                   String msg = new String(buffer.array());
                   System.out.println(msg.trim());
                }
                iterator.remove();
            }
        }else{
            //System.out.println("没有可用的通道...");
        }
    }

    public static void main(String[] args) throws IOException {
        ChatClient chatClient = new ChatClient("127.0.0.1",7777,"wangsenyan");
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (true){
                   chatClient.readMsg();
                   try {
                       Thread.sleep(3000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            chatClient.sendMsg(s);
        }
    }
}
