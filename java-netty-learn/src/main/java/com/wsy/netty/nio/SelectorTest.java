package com.wsy.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
 * - SocketChannel --- Socket
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
public class SelectorTest {
    public static void main(String[] args) throws IOException {
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

            }
        }
    }
}
