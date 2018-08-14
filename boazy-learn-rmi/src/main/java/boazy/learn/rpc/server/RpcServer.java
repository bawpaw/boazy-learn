package boazy.learn.rpc.server;

import boazy.learn.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class RpcServer {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(final Object service, int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);  // 启动服务监听

            while (true) { // 循环监听
                final Socket socket = serverSocket.accept(); // 监听服务

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        // 处理请求
                        ObjectInputStream inputStream = null;
                        ObjectOutputStream outputStream = null;
                        try {
                            inputStream = new ObjectInputStream(socket.getInputStream()); // 读取客户端的输入流
                            RpcRequest request = (RpcRequest) inputStream.readObject(); // 反序列化远程传输过来的RpcRequest对象
                            Object result = invoke(request); // 通过反射去调用本地的方法

                            outputStream = new ObjectOutputStream(socket.getOutputStream()); // 将结果输出给客户端
                            outputStream.writeObject(result);
                            outputStream.flush();
                        } catch (Exception e) {
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
                                    inputStream.close();
                                } catch (IOException e) {
                                    // 安全关闭
                                }
                            }
                        }
                    }

                    private Object invoke(RpcRequest request) throws Exception {
                        Object[] args = request.getParameters();
                        Class<?>[] types = new Class[args.length];
                        for (int i = 0, in = args.length; i < in; i++) {
                            types[i] = args[i].getClass();
                        }
                        Method method = service.getClass().getMethod(request.getMethodName(), types);
                        return method.invoke(service, args);
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    // 安全关闭
                }
            }
        }
    }

}
