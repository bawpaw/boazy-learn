package boazy.learn.rmi.server;

import boazy.learn.rmi.MyRMIService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class MyRMIServiceImpl extends UnicastRemoteObject implements MyRMIService {

    protected MyRMIServiceImpl() throws RemoteException {
    }

    @Override
    public String learning(String name) throws RemoteException {
        System.out.println(name + "进入课堂...");
        return name + "正在上课...";
    }

}
