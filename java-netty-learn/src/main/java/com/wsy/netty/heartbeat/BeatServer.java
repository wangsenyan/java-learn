package com.wsy.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class BeatServer {
    private String host;
    private int port;
    public BeatServer(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                     .channel(NioServerSocketChannel.class)
                     .handler(new LoggingHandler(LogLevel.INFO))//server端的handler
                     .childHandler(new ChannelInitializer<SocketChannel>() {//client端的handler
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                             ChannelPipeline pipeline = ch.pipeline();
                             //读,写,读与写
                             //触发的事件传递给下一个handler
                             pipeline.addLast(new IdleStateHandler(3,5,6, TimeUnit.SECONDS));
                             pipeline.addLast(new BeatServerHandler());
                         }
                     });
            ChannelFuture cf = bootstrap.bind(port).sync();
            cf.channel().closeFuture().sync();

        }finally {
           bossGroup.shutdownGracefully();
           workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new BeatServer("127.0.0.1",8888).run();
    }
}
