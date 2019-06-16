package com.github.sinoboazy.learn.rpc4netty.provider.core;

/**
 * @author boazy
 * @date 2019/6/17
 */
public @interface RpcService {
    String version() default "";
}
