package boazy.learn.rmi.client;

import boazy.learn.rmi.MyRMIService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class ClientApp {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        System.out.println("rmi invoke start...");

        MyRMIService rmiService
                = (MyRMIService) Naming.lookup("rmi://127.0.0.1/" + MyRMIService.class.getName());
        String resp = rmiService.learning("boazy180814");
        System.out.println("resp: " + resp);

        System.out.println("rmi invoke end.");
    }

}
