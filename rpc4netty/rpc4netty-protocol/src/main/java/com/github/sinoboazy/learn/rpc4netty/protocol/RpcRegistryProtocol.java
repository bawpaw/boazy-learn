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
     * 服务名（采用className）
     */
    private String serviceName;
    /**
     * 服务方法名
     */
    private String methodName;
    /**
     * 参数类型数组
     */
    private Class<?>[] argsClass;
    /**
     * 参数值数组
     */
    private Object[] args;

}
