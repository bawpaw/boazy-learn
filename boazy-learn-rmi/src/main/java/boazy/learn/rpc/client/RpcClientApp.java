package boazy.learn.rpc.client;

import boazy.learn.rpc.MyRpcService;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class RpcClientApp {

    public static void main(String[] args) {
        System.out.println("rpc invoke start...");

        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        MyRpcService myRpcService = rpcClientProxy.clientProxy(MyRpcService.class, "localhost", 9090);
        String resp = myRpcService.learning("boazy");
        System.out.println("resp: " + resp);

        System.out.println("rmi invoke end.");
    }

}
