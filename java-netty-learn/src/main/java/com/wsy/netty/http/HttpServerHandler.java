package com.wsy.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 出现的问题:
 * 1. 8000以下端口不能访问
 * 2. 乱码问题:charset=utf-8
 *    - response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
 * 3. 浏览器发错两次请求
 *    - 请求的地址
 *    - favicon.ico
 * 4. 过滤资源
 * 5. pipeline/handler 每个浏览器不一样?线程不一样就不一样吧
 *    短连接,断开重连线程不一样,导致pipeline/handler也不一样
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    //读取客户端数据
    //SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter
    //HttpObject 客户端和服务端相互通信的数据封装
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断msg是不是httpRequest请求

        if(msg instanceof HttpRequest){
            System.out.println("pipeline hashcode=" + ctx.pipeline().hashCode() + "this hashcode=" + this.hashCode());
            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());
            //回复信息给浏览器 [http协议]
            HttpRequest request = (HttpRequest)msg;
            URI uri = new URI(request.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了favicon.ico");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("hello.我是服务器", CharsetUtil.UTF_8 );
            //构造一个http的响应,即httpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            //将构建好的response
            ctx.writeAndFlush(response);
        }
    }
}
