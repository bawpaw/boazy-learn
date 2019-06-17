package com.github.sinoboazy.learn.rpc4netty.provider;

import com.github.sinoboazy.learn.rpc4netty.provider.core.RpcProviderServer;
import com.github.sinoboazy.learn.rpc4netty.provider.core.RpcRegistryService;
import com.github.sinoboazy.learn.rpc4netty.provider.core.SimpleIocContainer;

/**
 * @author boazy
 * @date 2019/6/17
 */
public class ProviderApp {

    // 本应用端口
    private static int thisAppPort = 8181;
    private static String packagePath = "com.github.sinoboazy.learn.rpc4netty.provider.service";

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        // 1、扫描服务并注册到Ioc容器
        SimpleIocContainer.scanService(packagePath);
        // 2、扫描RPC服务注册到注册中心
        new RpcRegistryService("127.0.0.1", 8180)
                .registryService("127.0.0.1", thisAppPort);
        // 3、启动Server监听
        new RpcProviderServer().startServer(thisAppPort);

    }

}
