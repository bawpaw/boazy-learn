package com.github.sinoboazy.learn.rpc4netty.provider.core;

import com.github.sinoboazy.learn.rpc4netty.protocol.RpcTransportProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * @author boazy
 * @date 2019/6/17
 */
public class RpcRequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            Object result = new Object();
            RpcTransportProtocol request = (RpcTransportProtocol) msg;

            // 当客户端建立连接时，需要从自定义协议中获取信息，拿到具体的服务和实参
            // 使用反射调用
            if (SimpleIocContainer.containsKey(request.getServiceName())) {
                Object service = SimpleIocContainer.get(request.getServiceName());
                Method method = service.getClass().getMethod(request.getMethodName(), request.getArgsClass());
                result = method.invoke(service, request.getArgs());
            }

            ctx.write(result);
        } finally {
            ctx.flush();
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        try {
            throw new RuntimeException(cause);
        } finally {
            ctx.close();
        }

    }

}
