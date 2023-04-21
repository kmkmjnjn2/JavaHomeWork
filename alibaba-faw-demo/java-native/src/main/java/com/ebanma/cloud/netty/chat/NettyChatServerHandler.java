package com.ebanma.cloud.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WHY
 * @version $ Id: NettyChatServerHandler, v 0.1 2023/04/21 11:24 kmkmj Exp $
 */
public class NettyChatServerHandler extends SimpleChannelInboundHandler<String> {


    private static List<Channel> channels = new ArrayList<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();
        for (Channel channel1 : channels) {
            if (channel1 != channel) {
                channel1.writeAndFlush("["+channel.remoteAddress().toString().substring(1)+"]说"+s);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.add(channel);
        System.out.println("[Server]:" + channel.remoteAddress().toString().substring(1) + "上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.remove(channel);
        System.out.println("[Server]:" + channel.remoteAddress().toString().substring(1) + "下线了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        channels.remove(channel);
        System.out.println("[Server]:" + channel.remoteAddress().toString().substring(1) + "异常");

    }
}