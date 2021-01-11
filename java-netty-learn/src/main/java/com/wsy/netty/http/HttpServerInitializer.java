package com.wsy.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //向管道加入处理器
        //得到管道
        ChannelPipeline pipeline = ch.pipeline();
        //加入编解码器
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast("httpAggregator",new HttpObjectAggregator(512 * 1024));
        //netty提供的http编解码器
        //pipeline.addLast("MyHttpServerCodes",new HttpServerCodec());
        //增加自己的handler
        pipeline.addLast("MyHttpServerHandler",new HttpServerHandler());
    }
}
