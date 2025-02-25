package net_demo.netty;

import ch.qos.logback.classic.Logger;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.LoggerFactory;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = (Logger) LoggerFactory.getLogger(NettyClient.class);

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            ChannelPipeline pip = ch.pipeline();
                            pip.addLast(new StringDecoder());
                            pip.addLast(new StringEncoder());
                            pip.addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    logger.info("Received: {}" , msg);
                                    ctx.writeAndFlush(msg);
                                }

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) {
                                    logger.info("建立连接 = {}", ctx.channel().remoteAddress());
                                    ctx.writeAndFlush("Hello World2222");
                                }

                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) {
                                    logger.info("Channel is inactive,remoteAddress = {} ", ctx.channel().remoteAddress());
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                    logger.info("Exception Caught: {}", cause.getMessage());
                                }
                            });
                        }
                    });

            ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();
            future.channel().writeAndFlush("Hello Server");
            logger.info("Client connected");
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
