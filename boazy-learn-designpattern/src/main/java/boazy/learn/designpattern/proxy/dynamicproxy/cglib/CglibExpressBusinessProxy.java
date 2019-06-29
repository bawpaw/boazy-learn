package boazy.learn.designpattern.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author boazy
 * @date 2019/4/27
 */
public class CglibExpressBusinessProxy implements MethodInterceptor {

    public <T> T getInstance(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();

        //要把哪个设置为即将生成的新类父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        //noinspection unchecked
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //业务的增强
        before();
        Object obj = methodProxy.invokeSuper(o, objects);
        after();
        return obj;
    }

    private void before() {
        System.out.println("[cglib]通知快递公司取快递，发送取快递码...");
        System.out.println("[cglib]------------------------------");
    }

    private void after() {
        System.out.println("[cglib]------------------------------");
        System.out.println("[cglib]验证取快递码，将快递给快递员邮寄...");
    }

}
