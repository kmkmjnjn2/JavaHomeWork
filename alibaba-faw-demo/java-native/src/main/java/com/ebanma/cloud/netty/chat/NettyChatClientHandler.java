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
 * @version $ Id: NettyChatClientHandler, v 0.1 2023/04/21 11:26 kmkmj Exp $
 */
public class NettyChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }
}