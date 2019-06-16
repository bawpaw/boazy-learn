package com.github.sinoboazy.learn.rpc4netty.protocol;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义RPC传输协议（用户registry与consumer和provider通信）
 *
 * @author boazy
 * @date 2019/6/17
 */
@Builder
@Data
public class RpcTransportProtocol implements Serializable {
    private static final long serialVersionUID = 3030653625573025558L;

    /**
     * 服务名（采用className）
     */
    private String serviceName;
    /**
     * 服务版本
     */
    private String version;

}
