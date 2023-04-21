package com.ebanma.cloud.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.Socket;

/**
 * @author WHY
 * @version $ Id: NettyServer, v 0.1 2023/04/21 10:18 kmkmj Exp $
 */
public class NettyServer {

    /**
     * 1.配置线程池
     * 2.Channel 初始化
     * 3.端口绑定
     */
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;

        try{
//        1. 创建bossGroup线程组: 处理网络事件--连接事件
         bossGroup = new NioEventLoopGroup(1);
//        2. 创建workerGroup线程组: 处理网络事件--读写事件
         workerGroup = new NioEventLoopGroup();
//        3. 创建服务端启动助手
        ServerBootstrap boot = new ServerBootstrap();
//        4. 设置bossGroup线程组和workerGroup线程组
        boot.group(bossGroup, workerGroup)
//        5. 设置服务端通道实现为NIO
                .channel(NioServerSocketChannel.class)
//        6. 参数设置
                .childOption(ChannelOption.SO_KEEPALIVE,Boolean.TRUE)
//        7. 创建一个通道初始化对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                //        8. 向pipeline中添加自定义业务处理handler
                                .addLast(new NettyServerHandler());
                    }
                });

//        9. 启动服务端并绑定端口,同时将异步改为同步
        ChannelFuture future = boot.bind(9999).sync();
//        10. 关闭通道和关闭连接池
        future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}