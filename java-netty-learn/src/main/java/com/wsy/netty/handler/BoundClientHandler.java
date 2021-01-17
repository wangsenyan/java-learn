package com.wsy.netty.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class BoundClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("服务器ip=" + ctx.channel().remoteAddress());
        System.out.println("收到服务器消息=" +msg);
    }
    //重写channelActive发送数据

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("BoundClientHandler 发送数据");
        ctx.writeAndFlush(123456L);//发送的是一个long
        //ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabababab", Charset.forName("utf-8")));
    }
}
