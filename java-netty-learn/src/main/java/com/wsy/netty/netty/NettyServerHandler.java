package com.wsy.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器:" + Thread.currentThread().getName());
        System.out.println("server ctx=" + ctx);
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端消息:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:" + ctx.channel().remoteAddress());

        //将任务加到taskQueue,否则会阻塞channelReadComplete
        //方案一:程序自定义普通任务
        //      同一个线程,第二还需再等待20s
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("你好,客户端", CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        });
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("你也好,客户端", CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        });
        //方案二:定时任务 scheduleQueue
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("你也好,定时器", CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        },5, TimeUnit.SECONDS); //5 秒,单线程在上一个任务结束后才会计时
        System.out.println("go on......");
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
