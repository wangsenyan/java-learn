package com.wsy.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class BoundServerDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("BoundServerDecoder2 被调用");
        //在ReplayingDecoder 不需要判断数据是否足够读取,内部直接会进行处理判断
        //ReplayingDecoder并不是所有的ByteBuffer操作都被支持,如果不支持,抛出UnsupportedOperationException
        //在某些情况下可能会稍微慢于ByteToMessageDecoder,例如网络缓慢并且消息格式复杂时,消息会被拆成多个碎片,速度变慢
        out.add(in.readLong());
    }
}
