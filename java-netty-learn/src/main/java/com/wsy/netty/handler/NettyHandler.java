package com.wsy.netty.handler;

import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.http.HttpObjectDecoder;

/**
 * 入站:从socket到服务器
 * 出站:从服务器到socket
 * 解码器：
 *  - ByteToMessageDecoder
 *  - ReplayingDecoder 继承 ByteToMessageDecoder
 *  - LineBasedFrameDecoder 使用尾行控制字符(\n或者\r\n) 作为分隔符来解析数据
 *  - DelimiterBasedFrameDecoder 使用自定义的特殊字符作为消息的分隔符
 *  - HttpObjectDecoder -个http数据的解码器
 *  - LengthFieldBasedFrameDecoder 通过指定长度来标识整包消息,这样皆可以自动的处理黏包和半包消息
 */
public class NettyHandler {
    public static void main(String[] args) {

    }
}
