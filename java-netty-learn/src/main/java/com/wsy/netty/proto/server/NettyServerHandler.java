package com.wsy.netty.proto.server;

import com.wsy.netty.proto.MsgProto;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends SimpleChannelInboundHandler<MsgProto> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgProto msg) throws Exception {
        //接收到数据并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入缓冲并刷新
        //对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();//关闭通道
    }
}
