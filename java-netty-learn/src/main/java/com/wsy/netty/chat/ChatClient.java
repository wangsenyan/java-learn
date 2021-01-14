package com.wsy.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class ChatClient {
    private String host;
    private int port;
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void run() throws Exception {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder",new StringEncoder());
                            pipeline.addLast(new ChatClientHandler());
                        }
                    });
            ChannelFuture cf = bootstrap.connect(host, port).sync();
            Channel ch = cf.channel();
            System.out.println("------" + ch.localAddress() + "--------");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String msg = scanner.next();
                ch.writeAndFlush(msg +"\r\n");
            }
        }finally {
            workGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        new ChatClient("127.0.0.1",8888).run();
    }
}
