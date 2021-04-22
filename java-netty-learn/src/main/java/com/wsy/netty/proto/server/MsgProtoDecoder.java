package com.wsy.netty.proto.server;

import com.wsy.netty.proto.MsgProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MsgProtoDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MsgProtoDecoder decode 被调用");
        //将二进制字节符转换为字节码
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        //封装成MsgProto,放入out,床底给下一个handler业务处理
        MsgProto msgProto = new MsgProto();
        msgProto.setLen(len);
        msgProto.setContent(bytes);
        out.add(msgProto);
    }
}
