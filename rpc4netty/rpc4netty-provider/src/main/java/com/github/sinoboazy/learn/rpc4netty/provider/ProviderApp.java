package com.github.sinoboazy.learn.rpc4netty.provider;

import com.github.sinoboazy.learn.rpc4netty.provider.core.RpcProviderServer;
import com.github.sinoboazy.learn.rpc4netty.provider.core.SimpleIocContainer;

/**
 * @author boazy
 * @date 2019/6/17
 */
public class ProviderApp {

    // 服务注册中心url
    private static String registryUrl = "http://127.0.0.1:8180/registry";
    // 本应用端口
    private static int thisAppPort = 8181;
    private static String packagePath = "com.github.sinoboazy.learn.rpc4netty.provider.service";

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        // 1、扫描服务并注册到Ioc容器
        SimpleIocContainer.scanService(packagePath);
        // 2、扫描RPC服务注册到注册中心
        // TODO
        // 3、启动Server监听
        new RpcProviderServer().startServer(thisAppPort);

    }

}
