package boazy.learn.rpc.client;

import boazy.learn.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class TcpTransport {

    private String host;
    private int port;

    public TcpTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket createSocket() {
        Socket socket;
        try {
            socket = new Socket(host, port);
            return socket;
        } catch (Exception e) {
            System.err.println("创建Socket失败！");
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Object send(RpcRequest request) {
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            socket = createSocket();

            outputStream = new ObjectOutputStream(socket.getOutputStream()); // 获取输出流，将请求传送给服务器
            outputStream.writeObject(request);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream()); // 获取输入流，取得服务端响应的结果
            Object result = inputStream.readObject();
            return result;
        } catch (Exception e) {
            System.out.println("发接远程调用失败！");
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // 安全关闭
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // 安全关闭
                }
            }
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // 安全关闭
                }
            }
        }
    }

}
