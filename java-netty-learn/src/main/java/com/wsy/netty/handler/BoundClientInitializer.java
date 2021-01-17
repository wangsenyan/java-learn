package com.wsy.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class BoundClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //加入一个出站的handler对数据进行一个编码
        //再加入一个自定义的handler处理业务
        pipeline.addLast(new BoundClientEncoder());
        //pipeline.addLast(new BoundServerDecoder());//入站的解码器
        pipeline.addLast(new BoundServerDecoder2());//入站的解码器
        pipeline.addLast(new BoundClientHandler());
    }
}
