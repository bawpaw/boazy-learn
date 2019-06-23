package com.github.sinoboazy.learn.rpc4netty.consumer.proxy;

import com.github.sinoboazy.learn.rpc4netty.protocol.RpcTransportProtocol;
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
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author boazy
 * @date 2019/6/18
 */
public class RpcServiceProxy {

    public static <T> T create(Class<T> interfaceClass, String serviceName, Integer servicePort) {
        RpcProxy proxy = RpcProxy.builder()
                .serviceClass(interfaceClass)
                .serviceName(serviceName)
                .servicePort(servicePort)
                .build();
        Class<?>[] interfaces = interfaceClass.isInterface() ?
                new Class[]{interfaceClass} :
                interfaceClass.getInterfaces();
        T proxyService = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), interfaces, proxy);
        return proxyService;
    }

    @Builder
    @Data
    private static class RpcProxy implements InvocationHandler {
        private Class<?> serviceClass;
        private String serviceName;
        private Integer servicePort;

        public Object invoke(Object proxy, Method method, Object[] args) {
            // 如果是一个已实现的具体类，忽略直接实际本地具体类实例调用
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("学习代码异常直接转换为运时异常抛出...", e);
                }
            }

            // rcp调用
            return rpcInvoke(method, args);
        }

        private Object rpcInvoke(Method method, Object[] args) {

            // 传输协议封装
            RpcTransportProtocol msg = RpcTransportProtocol.builder()
                    .serviceName(this.serviceClass.getName())
                    .methodName(method.getName())
                    .argsClass(method.getParameterTypes())
                    .args(args)
                    .build();

            final RpcServiceProxyHandler consumerHandler = new RpcServiceProxyHandler();
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                //自定义协议解码器
                                /** 入参有5个，分别解释如下
                                 maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出TooLongFrameException。
                                 lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
                                 lengthFieldLength：长度字段的长度：如：长度字段是int型表示，那么这个值就是4（long型就是8）
                                 lengthAdjustment：要添加到长度字段值的补偿值
                                 initialBytesToStrip：从解码帧中去除的第一个字节数
                                 */
                                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                //自定义协议编码器
                                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                                //对象参数类型编码器
                                pipeline.addLast("encoder", new ObjectEncoder());
                                //对象参数类型解码器
                                pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                                pipeline.addLast("handler", consumerHandler);
                            }
                        });

                ChannelFuture future = bootstrap.connect(serviceName, servicePort).sync();
                future.channel().writeAndFlush(msg).sync();
                future.channel().closeFuture().sync();
            } catch (Throwable t) {
                throw new RuntimeException("学习代码异常直接转换为运时异常抛出...", t);
            } finally {
                group.shutdownGracefully();
            }

            return consumerHandler.getResponse();
        }
    }

}



