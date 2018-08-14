package boazy.learn.rpc.server;

import boazy.learn.rpc.MyRpcService;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class MyRpcServiceImpl implements MyRpcService {
    @Override
    public String learning(String name) {
        System.out.println(name + "进入RPC课堂...");
        return name + "正在上课RPC...";
    }
}
