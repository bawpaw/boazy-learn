package com.github.sinoboazy.learn.rpc4netty.provider.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author boazy
 * @date 2019/6/17
 */
public class RpcProviderServer {

    public void startServer(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 自定义协议解码器
                            // maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出TooLongFrameException。
                            // lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
                            // lengthFieldLength：长度字段的长度。如：长度字段是int型表示，那么这个值就是4（long型就是8）
                            // lengthAdjustment：要添加到长度字段值的补偿值
                            // initialBytesToStrip：从解码帧中去除的第一个字节数
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(
                                    Integer.MAX_VALUE, 0, 4, 0, 4));
                            // 自定义协议编码器
                            pipeline.addLast(new LengthFieldPrepender(4));

                            // 对象参数类型编码器
                            pipeline.addLast("encoder", new ObjectEncoder());
                            // 对象参数类型解码器
                            pipeline.addLast("decoder", new ObjectDecoder(
                                    Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                            pipeline.addLast(new RpcRequestHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println(StringUtils.join("rpc4netty Provider started listen at ", port));
            future.channel().closeFuture().sync();
        } catch (Throwable e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

            e.printStackTrace();
        }
    }

}  
