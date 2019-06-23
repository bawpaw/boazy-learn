package com.github.sinoboazy.learn.rpc4netty.consumer.service;

import com.github.sinoboazy.learn.rpc4netty.api.RpcHelloService;
import com.github.sinoboazy.learn.rpc4netty.consumer.core.RpcReference;

/**
 * @author boazy
 * @date 2019/6/23
 */
public class ConsumerService {

    @RpcReference
    private RpcHelloService rpcHelloService;

    public String xxxHello(String name) {
        String hello = rpcHelloService.hello(name);
        return hello;
    }

}
