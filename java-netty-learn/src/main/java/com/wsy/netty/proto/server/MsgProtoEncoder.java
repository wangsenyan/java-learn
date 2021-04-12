package com.wsy.netty.proto.server;

import com.wsy.netty.proto.MsgProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgProtoEncoder extends MessageToByteEncoder<MsgProto> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MsgProto msg, ByteBuf out) throws Exception {
        System.out.println("MsgProtoEncoder 方法被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
