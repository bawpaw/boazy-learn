package com.github.sinoboazy.learn.rpc4netty.consumer.core;

import com.alibaba.fastjson.JSONObject;
import com.github.sinoboazy.learn.rpc4netty.protocol.RpcRegistryProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author boazy
 * @date 2019/6/18
 */
public class RpcRegistryService {

    private String registryHost;
    private int registryPort;

    public RpcRegistryService(String registryHost, int registryPort) {
        this.registryHost = registryHost;
        this.registryPort = registryPort;
    }

    public void registryService(String host, int port) {
        for (String key : SimpleIocContainer.SERVICE_MAP.keySet()) {
            Object service = SimpleIocContainer.SERVICE_MAP.get(key);
            RpcService rpcService = service.getClass().getAnnotation(RpcService.class);
            if (null == rpcService) {
                continue;
            }
            String serviceVersion = rpcService.version();

            registryService(key, serviceVersion, host, port);
        }
    }

    private void registryService(String serviceName, String version, String host, int port) {

        RpcRegistryProtocol msg = RpcRegistryProtocol.builder()
                .method("PUB")
                .serviceName(serviceName)
                .version(StringUtils.defaultIfBlank(version, ""))
                .host(host)
                .port(port)
                .build();

        //final RpcProxyHandler consumerHandler = new RpcProxyHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //自定义协议解码器
                            /* 入参有5个，分别解释如下
                             maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出TooLongFrameException。
                             lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
                             lengthFieldLength：长度字段的长度：如：长度字段是int型表示，那么这个值就是4（long型就是8）
                             lengthAdjustment：要添加到长度字段值的补偿值
                             initialBytesToStrip：从解码帧中去除的第一个字节数
                             */
                            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(
                                    Integer.MAX_VALUE, 0, 4, 0, 4));
                            //自定义协议编码器
                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));

                            //对象参数类型编码器
                            pipeline.addLast("encoder", new ObjectEncoder());
                            //对象参数类型解码器
                            pipeline.addLast("decoder", new ObjectDecoder(
                                    Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                            pipeline.addLast("handler", new ConsumerHandler());
                        }
                    });

            ChannelFuture future = b.connect(registryHost, registryPort).sync();
            future.channel().writeAndFlush(msg).sync();
            future.channel().closeFuture().sync();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        } finally {
            group.shutdownGracefully();
        }
    }

    private class ConsumerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("channelRead msg：" + JSONObject.toJSONString(msg));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("client exception is general");
        }
    }

}
