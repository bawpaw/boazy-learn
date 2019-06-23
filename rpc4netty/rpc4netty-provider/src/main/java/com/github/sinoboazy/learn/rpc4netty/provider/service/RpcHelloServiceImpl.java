package com.github.sinoboazy.learn.rpc4netty.provider.service;

import com.github.sinoboazy.learn.rpc4netty.api.RpcHelloService;
import com.github.sinoboazy.learn.rpc4netty.provider.core.RpcService;
import org.apache.commons.lang3.StringUtils;

/**
 * @author boazy
 * @date 2019/6/17
 */
@RpcService
public class RpcHelloServiceImpl implements RpcHelloService {

    @Override
    public String hello(String name) {
        String result = StringUtils.join("Hello ", name, "! I am rpc4netty.");
        System.out.println("响应：" + result);
        return result;
    }

}
