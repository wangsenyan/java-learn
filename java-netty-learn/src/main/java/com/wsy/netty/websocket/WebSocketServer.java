package com.wsy.netty.websocket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {
    private String host;
    private int port;
    public WebSocketServer(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))//server端的handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {//client端的handler
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //基于http协议,使用http的编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            //以块方式写,添加ChunkedWriteHandler处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            //1.http数据在传输过程中分段,HttpObjectAggregator多段聚合
                            //2.当浏览器发送大量数据,会发出多次http请求
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            //1. 对应websocket,它的数据是以帧(frame)形式传递
                            //2. 可以看到webSocketFrame下面有六个子类
                            //3. 浏览器请求ws://host:port/hello表示请求的path
                            //4. WebSocketServerProtocolHandler 核心功能是将http协议升级为websocket协议
                            //5. 101协议转换 http->websocket 101 Switching Protocols
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            pipeline.addLast(new WebSocketServerHandler());
                        }
                    });
            ChannelFuture cf = bootstrap.bind(port).sync();
            cf.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new WebSocketServer("127.0.0.1",8888).run();
    }
}
