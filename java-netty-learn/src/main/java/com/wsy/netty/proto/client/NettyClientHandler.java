package com.wsy.netty.proto.client;

import com.wsy.netty.proto.MsgProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NettyClientHandler extends SimpleChannelInboundHandler<MsgProto> {
    //当通道就绪就会触发改方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client " + ctx);
        for (int i = 0; i < 10; i++) {
            String msg = "尼玛的大傻逼";
            byte[] content = msg.getBytes(StandardCharsets.UTF_8);
            MsgProto msgProto = new MsgProto();
            msgProto.setContent(content);
            msgProto.setLen(content.length);
            ctx.writeAndFlush(msgProto);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgProto msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println(new String(content, Charset.forName("utf-8")));
    }
}
