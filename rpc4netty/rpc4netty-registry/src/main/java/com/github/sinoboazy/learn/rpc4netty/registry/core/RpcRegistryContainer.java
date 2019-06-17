package com.github.sinoboazy.learn.rpc4netty.registry.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author boazy
 * @date 2019/6/18
 */
public class RpcRegistryContainer {

    private static final ConcurrentHashMap<String, Object> REGISTRY_MAP = new ConcurrentHashMap<>(256);

    public static void add(String key, Object service) {
        REGISTRY_MAP.put(key, service);
    }

    public static Object get(String key) {
        return REGISTRY_MAP.get(key);
    }

    public static boolean containsKey(String key) {
        return REGISTRY_MAP.containsKey(key);
    }

}
