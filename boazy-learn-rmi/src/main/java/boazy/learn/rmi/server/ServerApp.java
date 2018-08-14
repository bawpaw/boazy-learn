package boazy.learn.rmi.server;

import boazy.learn.rmi.MyRMIService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 接口继承java.rmi.Remote，接口方法要throws RemoteException
 * 实现继承java.rmi.server.UnicastRemoteObject，实现方法throws RemoteException
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class ServerApp {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("server start...");

        // 创建服务实例
        MyRMIService myRMIService = new MyRMIServiceImpl();
        // 创建注册中心
        LocateRegistry.createRegistry(1099);
        // 向注册中心发布服务
        Naming.rebind("rmi://127.0.0.1/" + MyRMIService.class.getName(), myRMIService);

        System.out.println("server started.");
    }

}
