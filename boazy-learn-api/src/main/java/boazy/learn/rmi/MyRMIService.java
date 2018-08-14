package boazy.learn.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public interface MyRMIService extends Remote {
    String learning(String name) throws RemoteException;
}
