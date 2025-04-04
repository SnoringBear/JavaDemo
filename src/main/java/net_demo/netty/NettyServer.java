package net_demo.netty;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.LoggerFactory;

public class NettyServer {
    public static void main(String[] args) throws Exception {
        Logger logger = (Logger) LoggerFactory.getLogger(NettyServer.class);
        logger.setLevel(Level.DEBUG);
        // 实际默认线程数 NettyRuntime.availableProcessors() * 2:物理机CPU核心数的2倍
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pip = ch.pipeline();
                            pip.addLast(new StringDecoder());
                            pip.addLast(new StringEncoder());
                            pip.addLast(new MyHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind(9999).sync();
            logger.info("Server started on port 9999");
            future.channel().closeFuture().addListener(future1 -> logger.info("tcp关闭监听:{}", 8080)).sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    static class MyHandler extends SimpleChannelInboundHandler<String> {
        Logger logger = (Logger) LoggerFactory.getLogger("io.net_demo.netty");

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) {
            logger.info("Received: {}" , msg);
            ctx.writeAndFlush("Message received\n");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            logger.info("ChannelActive");
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) {
            logger.info("ChannelInactive");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            logger.info("exceptionCaught");
        }
    }
}
