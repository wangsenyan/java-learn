package com.wsy.netty.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
//--add-opens java.base/jdk.internal.misc=ALL-UNNAMED  -Dio.netty.tryReflectionSetAccessible=true
public class BoundClient {
    public static void main(String[] args) throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                     .channel(NioSocketChannel.class)
                     .handler(new BoundClientInitializer());//自定义初始化类
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 8888);
            cf.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }
}
