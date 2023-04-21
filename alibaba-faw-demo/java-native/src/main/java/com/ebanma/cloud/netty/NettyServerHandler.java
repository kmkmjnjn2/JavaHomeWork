package com.ebanma.cloud.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author WHY
 * @version $ Id: NettyServerHandler, v 0.1 2023/04/21 10:46 kmkmj Exp $
 */
public class NettyServerHandler implements ChannelInboundHandler {
    /**
     * @author: WangHaiyang
     * @date: 2023/4/21 10:48
     * @param channelHandlerContext
     * @param o
     * @description:通道读取事件
     */
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf byteBuf = (ByteBuf) o;
        System.out.println("客户端发过来的消息:" + byteBuf.toString(CharsetUtil.UTF_8));
    }
    /**
     * @author: WangHaiyang
     * @date: 2023/4/21 10:49
     * @param channelHandlerContext
     * @description:通道读取完毕事件
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("你好，我是Netty服务端", CharsetUtil.UTF_8));

    }


    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }



    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        System.out.println(throwable);
    }
}