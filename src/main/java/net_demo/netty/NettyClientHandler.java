package net_demo.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.CompletableFuture;

@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 1.根据请求id，获取对应future
        CompletableFuture future = FutureMapUtil.remove(((String) msg).split(":")[1]);
        // 2.如果存在，则设置future结果
        if (null != future) {
            future.complete(((String) msg).split(":")[0]);
        }
    }
}
