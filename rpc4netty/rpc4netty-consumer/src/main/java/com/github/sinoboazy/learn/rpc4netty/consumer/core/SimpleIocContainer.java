package com.github.sinoboazy.learn.rpc4netty.consumer.core;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author boazy
 * @date 2019/6/23
 */
public class SimpleIocContainer {

    public static final ConcurrentHashMap<String, Object> SERVICE_MAP = new ConcurrentHashMap<>(256);

    public static void add(String key, Object service) {
        SERVICE_MAP.put(key, service);
    }

    public static Object get(String key) {
        return SERVICE_MAP.get(key);
    }

    public static boolean containsKey(String key) {
        return SERVICE_MAP.containsKey(key);
    }

    public static void scanService(String packagePath)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        URL url = SimpleIocContainer.class.getClassLoader()
                .getResource(packagePath.replaceAll("\\.", "/"));
        if (null == url) {
            throw new NullPointerException("packagePath is null.");
        }

        File dir = new File(url.getFile());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                scanService(StringUtils.join(packagePath, ".", file.getName()));
                continue;
            }

            // 获取className
            String className = StringUtils.join(
                    packagePath, ".", file.getName().replace(".class", ""));
            // 获取class
            Class<?> clazz = Class.forName(className);
            if (null == clazz.getInterfaces() && clazz.getInterfaces().length > 0) {
                // 创建实例并注册到ioc容器中
                add(clazz.getInterfaces()[0].getName(), clazz.newInstance());
            } else {
                add(clazz.getName(), clazz.newInstance());
            }
        }
    }

}
