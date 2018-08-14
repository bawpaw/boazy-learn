package boazy.learn.rpc.server;

import boazy.learn.rpc.MyRpcService;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class RpcServerApp {

    public static void main(String[] args) {
        System.out.println("rpc server start...");

        MyRpcService myRpcService = new MyRpcServiceImpl();
        new RpcServer().publisher(myRpcService, 9090);
    }

}
