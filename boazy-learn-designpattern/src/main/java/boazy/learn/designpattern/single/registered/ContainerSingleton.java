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

    private static final Map<String, Object> IOC = new ConcurrentHashMap<>();

    public static final Object getInstance(String className) {
        synchronized (IOC) {
            if (!IOC.containsKey(className)) {
                return IOC.get(className);
            }

            Object obj;
            try {
                obj = Class.forName(className).newInstance();
                IOC.put(className, obj);
            } catch (Exception e) {
                throw new RuntimeException("Create a singleton instance exception");
            }
            
            return obj;
        }
    }

}
