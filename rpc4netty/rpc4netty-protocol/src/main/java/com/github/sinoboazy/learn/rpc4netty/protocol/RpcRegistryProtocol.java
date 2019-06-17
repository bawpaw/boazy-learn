package com.github.sinoboazy.learn.rpc4netty.protocol;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义RPC传输协议（用于consumer与provider通信）
 *
 * @author boazy
 * @date 2019/6/17
 */
@Builder
@Data
public class RpcRegistryProtocol implements Serializable {
    private static final long serialVersionUID = 3609633675201699157L;

    /**
     * 方法（SUB表示订阅服务、PUB表求发布服务）
     */
    private String method;
    /**
     * 服务名（采用className）
     */
    private String serviceName;
    /**
     * 服务版本
     */
    private String version;
    /**
     * 服务地址
     */
    private String host;
    /**
     * 服务端口
     */
    private int port;

}
