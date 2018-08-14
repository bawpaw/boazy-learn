package boazy.learn.rpc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public interface MyRpcService {
    String learning(String name);
}
