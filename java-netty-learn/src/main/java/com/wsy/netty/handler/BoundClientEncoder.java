package com.wsy.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
//BoundClientEncoder 父类是 MessageToByteEncoder
//MessageToByteEncoder的write方法,判断msg是不是应该处理的类型,如果是就处理,不是就跳过
//编写注意接收数据类型和返回数据类型
//入站事件按list自然顺序调用handler,出站反序调用handler处理
public class BoundClientEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("BoundClientEncoder encode 被调用");
        System.out.println("msg=" + msg);
        out.writeLong(msg);
    }
}
