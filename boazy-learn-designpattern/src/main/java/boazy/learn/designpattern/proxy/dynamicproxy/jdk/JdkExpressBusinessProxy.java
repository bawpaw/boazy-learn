package boazy.learn.designpattern.proxy.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author boazy
 * @date 2019/4/27
 */
public class JdkExpressBusinessProxy implements InvocationHandler {

    private Object target;

    public <T> T getInstance(T target) {
        this.target = target;
        Class<?> clazz = target.getClass();

        /**
         * clazz.getClassLoader() 代理目标对象的类加载器
         * clazz.getInterfaces() 代理目标对象类的实现接口class
         * JDK代理对象
         */
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target, args);
        after();
        return obj;
    }

    private void before() {
        System.out.println("[jdk]通知快递公司取快递，发送取快递码...");
        System.out.println("[jdk]------------------------------");
    }

    private void after() {
        System.out.println("[jdk]------------------------------");
        System.out.println("[jdk]验证取快递码，将快递给快递员邮寄...");
    }

}
