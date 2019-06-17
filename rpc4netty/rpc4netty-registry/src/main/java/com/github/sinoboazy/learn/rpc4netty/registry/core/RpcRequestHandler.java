package com.github.sinoboazy.learn.rpc4netty.registry.core;

import com.alibaba.fastjson.JSONObject;
import com.github.sinoboazy.learn.rpc4netty.protocol.RpcRegistryProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author boazy
 * @date 2019/6/18
 */
public class RpcRequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            RpcRegistryProtocol request = (RpcRegistryProtocol) msg;

            String serviceName = request.getServiceName();
            String serviceVersion = request.getVersion();
            String key = StringUtils.join(serviceName, serviceVersion);
            switch (request.getMethod()) {
                case "PUB":
                    RpcRegistryContainer.add(key, request);
                    System.out.println("发布服务成功：" + JSONObject.toJSONString(request));
                    break;
                case "SUB":
                    RpcRegistryProtocol rrp = (RpcRegistryProtocol) RpcRegistryContainer.get(key);
                    if (null == rrp) {
                        System.out.println("订阅服务失败：" + key);
                        break;
                    }
                    System.out.println("订阅服务成功：" + JSONObject.toJSONString(rrp));
                    ctx.write(rrp);
                    break;
                default:
                    break;
            }
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
