package com.wsy.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * SelectorImpl implement Selector;
 * 1，基本方法
 * - select() 阻塞返回已准备数据
 * - select(timeout) 阻塞timeout
 * - wakeup() 唤醒
 * - selectNow() //不阻塞
 * 2. 使用
 * - ServerSocketChannel --- ServerSocket
 *  - 在服务器端监听新的客户端Socket连接
 *  - open() 打开serverSocketChannel通道
 *  - accept
 *  - bind
 * - SocketChannel --- Socket
 *  - 更重数据的读写
 *  - 网络IO通道
 *  - open
 *  - configureBlocking
 *  - finishConnect
 *  - write
 *  - read
 *  - register
 *
 * 3.流程
 *   -1 当客户端连接时,会通过ServerSocketChannel得到SocketChannel
 *   -2 将SocketChannel注册到Selector上
 *     register(Selector,ops) 一个Selector上可以注册多个SocketChannel
 *   -3 注册后返回一个SelectionKey,会和该Selector关联(集合)
 *   -4 Selector进行监听,select方法会返回有事件发生的通道个数
 *   -5 进一步得到各个SelectKey(有事件发生)
 *   -6 在通过SelectKey反向获取SocketChannel
 *   -7 可以通过channel()得到channel，完成业务处理
 *   -8 代码...
 */
public class SelectorServer {
    public static void main(String[] args) throws Exception {
        //创建ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到selector
        Selector selector = Selector.open();
        //绑定一个端口ipv4
        serverSocketChannel.socket().bind(new InetSocketAddress(7001));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //ServerSocket先加入selector,关心OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            if(selector.select(1000)==0){
                System.out.println("我等了你1秒,你射了吗");
                //可以做其他事情
                continue;
            }
            //获取相关的selectionKey,关注事件的集合
            //通过selectionKey反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                System.out.println("key= "+key);
                //根据key对应的通道发生的事做相应的处理
                if(key.isAcceptable()){//发生OP_ACCEPTED
                    //给该客户端生成一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println(selector.selectedKeys().size());//发生事件的channel
                    System.out.println(selector.keys().size());//总共的
                    System.out.println("socketChannel=" + socketChannel.hashCode());
                    //这个也要非阻塞
                    //Exception in thread "main" java.nio.channels.IllegalBlockingModeException
                    socketChannel.configureBlocking(false);
                    //为该socketChannel注册到selector,关注事件为OP_READ,同时绑定buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if(key.isReadable()){//发生OP_READ
                    //通过key,反向获取对应的channel
                    SocketChannel channel = (SocketChannel)key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    //将可读数据写入attachment

                    channel.read(buffer);
                    System.out.println(new String(buffer.array()));
                }
                //手动从集合中移动当前的selectionKey,防止重复操作
                keyIterator.remove();
            }
        }
    }
}
