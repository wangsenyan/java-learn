package com.wsy.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 报错:jdk.internal.misc.Unsafe.allocateUninitializedArray(int):
 *      unavailable java.lang.IllegalAccessException:
 *      class io.netty.util.internal.PlatformDependent0$6
 *      cannot access class jdk.internal.misc.Unsafe
 *      (in module java.base) because module java.base
 *      does not export jdk.internal.misc to unnamed module
 * jvm:--add-opens java.base/jdk.internal.misc=ALL-UNNAMED
 *     -Dio.netty.tryReflectionSetAccessible=true
 *
 * pipeline 双向链表
 * ctx 双向链表
 * NioEventLoop串行化 处理:消息读取->解码->处理->编码->发送
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //bossGroup 和 workGroup 含有的子线程(NioEventLoop)
        //  默认实际是CPU核数 * 2,可以自己指定
        //  workgroup线程满后会重用某一个
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //创建服务端的启动配置,配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)//设置两个线程
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列等待连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保存活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("客户端socketChannel hashcode=" + ch.hashCode());
                            //可以用一个集合管理socketChannel,在推送消息时,可以将业务加入到各个channel
                            //对应的NiOEventLoop的taskQueue 或者 scheduleTaskQueue
                        }
                    }).childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道测试对象(匿名)
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务器已经装逼好了......");
            //绑定一个端口并同步处理
            //启动服务器(并绑定端口)
            ChannelFuture cf = bootstrap.bind(6000).sync();

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
