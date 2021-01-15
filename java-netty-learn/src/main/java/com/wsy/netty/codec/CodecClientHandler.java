package com.wsy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class CodecClientHandler extends ChannelInboundHandlerAdapter {
    //当通道就绪就会触发改方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //确定class传递
//        StudentPOJO.Student student = StudentPOJO.Student.newBuilder().setId(4).setName("豹子头林冲").build();
//        ctx.writeAndFlush(student);
        //随机的发送student 或者worker对象
        int random  = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if( 0 == random){
            myMessage =  MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.StudentType)
                    .setStudent(MyDataInfo.Student.newBuilder().setId(5).setName("黑旋风李逵").build())
                    .build();
        }else{
            myMessage =  MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.WorkerType)
                    .setWorker(MyDataInfo.Worker.newBuilder().setName("宋江").setAge(5).build())
                    .build();
        }
        ctx.writeAndFlush(myMessage);
    }

    //当通道有读取事件时,触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器消息:" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址:" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
