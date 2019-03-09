package boazy.learn.designpattern.single.registered;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器单例
 *
 * @author boazy
 * @date 2019/3/9
 */
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                return ioc.get(className);
            }

            Object obj = null;
            try {
                obj = Class.forName(className).newInstance();
                ioc.put(className, obj);
            } catch (Exception e) {
                throw new RuntimeException("Create a singleton instance exception");
            }
            return obj;
        }
    }

}
