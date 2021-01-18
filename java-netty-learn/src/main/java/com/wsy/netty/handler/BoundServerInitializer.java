package com.wsy.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class BoundServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //入站的handler进行解码
        //入站 decoder -> handler
        //出站 handler -> encoder
        //pipeline.addLast(new BoundServerDecoder());
        pipeline.addLast(new BoundServerDecoder2());
        pipeline.addLast(new BoundClientEncoder());
        pipeline.addLast(new BoundServerHandler());
        System.out.println("good");
    }
}

