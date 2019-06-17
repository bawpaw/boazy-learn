package com.github.sinoboazy.learn.rpc4netty.registry;

import com.github.sinoboazy.learn.rpc4netty.registry.core.RpcRegistryServer;

/**
 * @author boazy
 * @date 2019/6/18
 */
public class RegistryApp {

    // 本应用端口
    private static int thisAppPort = 8180;

    public static void main(String[] args) {
        // 启动注册中心并监听
        new RpcRegistryServer().startServer(thisAppPort);
    }

}
