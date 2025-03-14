package net_demo.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //5. 根据消息内容和请求id，拼接消息帧
    public String generatorFrame(String msg, String reqId) {
        return msg + ":" + reqId + "|";
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        //6.处理请求
        try {
            System.out.println(msg);
            // 6.1.获取消息体，并且解析出请求id
            String str = (String) msg;
            String reqId = str.split(":")[1];

            // 6.2.拼接结果，请求id,协议帧分隔符(模拟服务端执行服务产生结果)
            String resp =  generatorFrame("im jiaduo ", reqId);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 6.3.写回结果
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(resp.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}