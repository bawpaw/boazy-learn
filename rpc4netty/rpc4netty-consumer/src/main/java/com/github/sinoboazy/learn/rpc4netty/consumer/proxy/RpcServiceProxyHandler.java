package com.github.sinoboazy.learn.rpc4netty.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author boazy
 * @date 2019/6/18
 */
public class RpcServiceProxyHandler extends ChannelInboundHandlerAdapter {
	  
    private Object response;    
      
    public Object getResponse() {    
	    return response;    
	}    
  
    @Override    
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
        response = msg;
    }    
        
    @Override    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {    
        System.out.println("client exception is general");    
    }    
} 
