package com.github.sinoboazy.learn.rpc4netty.consumer.core;

import com.github.sinoboazy.learn.rpc4netty.consumer.proxy.RpcServiceProxy;
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
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * @author boazy
 * @date 2019/6/23
 */
public class RpcReferenceService {

    private String registryHost;
    private int registryPort;

    public RpcReferenceService(String registryHost, int registryPort) {
        this.registryHost = registryHost;
        this.registryPort = registryPort;
    }

    public void referenceService() {

        for (String key : SimpleIocContainer.SERVICE_MAP.keySet()) {
            Object service = SimpleIocContainer.SERVICE_MAP.get(key);

            Field[] declaredFields = service.getClass().getDeclaredFields();
            if (1 > declaredFields.length) {
                return;
            }

            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);

                RpcReference rpcReference = declaredField.getAnnotation(RpcReference.class);
                if (null == rpcReference) {
                    continue;
                }
                String serviceVersion = rpcReference.version();

                RpcRegistryProtocol rpcRegistryProtocol = (RpcRegistryProtocol) referenceService(
                        declaredField.getType().getName(), serviceVersion, registryHost, registryPort);

                Object proxyObject = RpcServiceProxy.create(declaredField.getType()
                        , rpcRegistryProtocol.getHost(), rpcRegistryProtocol.getPort());
                try {
                    declaredField.set(service, proxyObject);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("学习代码异常直接转换为运时异常抛出...", e);
                }
            }
        }
    }

    private Object referenceService(String serviceName, String serviceVersion, String host, int port) {

        RpcRegistryProtocol msg = RpcRegistryProtocol.builder()
                .method("SUB") // 订阅服务
                .serviceName(serviceName)
                .version(StringUtils.defaultIfBlank(serviceVersion, ""))
                .host(host)
                .port(port)
                .build();

        final ConsumerHandler consumerHandler = new ConsumerHandler();
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

                            pipeline.addLast("handler", consumerHandler);
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

        return consumerHandler.getResponse();
    }

    private class ConsumerHandler extends ChannelInboundHandlerAdapter {
        @Getter
        private Object response;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            response = msg;
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            System.out.println("client exception is general");
            throw new RuntimeException("学习代码异常直接转换为运时异常抛出...", cause);
        }
    }

}
