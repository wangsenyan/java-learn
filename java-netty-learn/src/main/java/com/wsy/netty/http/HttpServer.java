package com.wsy.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class HttpServer {
    public static void main(String[] args)  throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer());
            System.out.println("服务器已经装逼好了......");
            ChannelFuture cf = bootstrap.bind(new InetSocketAddress(8000)).sync();
            cf.addListener(future -> {
                if(future.isSuccess())
                    System.out.println("端口[6000]监听成功" );
                else
                    System.out.println("端口[6000]监听失败");
            });
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
