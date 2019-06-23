package com.github.sinoboazy.learn.rpc4netty.consumer;

import com.github.sinoboazy.learn.rpc4netty.consumer.core.RpcReferenceService;
import com.github.sinoboazy.learn.rpc4netty.consumer.core.SimpleIocContainer;
import com.github.sinoboazy.learn.rpc4netty.consumer.service.ConsumerService;

/**
 * @author boazy
 * @date 2019/6/18
 */
public class ConsumerApp {

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        // 1、扫描服务并注册到Ioc容器
        SimpleIocContainer.scanService("com.github.sinoboazy.learn.rpc4netty.consumer.service");
        // 2、扫描RPC服务从注册中心获取地址端口信息
        new RpcReferenceService("127.0.0.1", 8180).referenceService();
        // 3、远程调用
        // 获取ioc容器的服务
        ConsumerService consumerService = (ConsumerService)
                SimpleIocContainer.get("com.github.sinoboazy.learn.rpc4netty.consumer.service.ConsumerService");
        // 远程调用
        String result = consumerService.xxxHello("boazy");
        // 远程调用结果
        System.out.println("远程调用结果：" + result);
    }

}
