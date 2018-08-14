package boazy.learn.rpc.client;

import boazy.learn.rpc.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class RpcClientProxy {

    public <T> T clientProxy(Class<T> serviceClass, final String host, final int port) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        RpcRequest request = new RpcRequest(); // 接写请求
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameters(args);

                        TcpTransport tcpTransport = new TcpTransport(host, port); // tcp传输协议传输

                        return tcpTransport.send(request); // 发送请求
                    }
                });
    }

}
