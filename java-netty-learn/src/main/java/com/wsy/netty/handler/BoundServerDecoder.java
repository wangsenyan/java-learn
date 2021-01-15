package com.wsy.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class BoundServerDecoder extends ByteToMessageDecoder {
    /**
     *
     * @param ctx 上下文对象
     * @param in  入站的byteBuf
     * @param out list集合,将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()>=8){
            out.add(in.readLong());
        }
    }
}
