package boazy.learn.designpattern.single;

import java.io.Serializable;

/**
 * 饥饿单例模型
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/4
 */
public class HungerSingle {
    // 1、私有静态成员常量初始本类实例(类加载时初始化实例)
    private static final HungerSingle HUNGER = new HungerSingle();

    // 2、构造方法私有化
    private HungerSingle() {
    }

    // 3、提供静态获取实例方法
    public static HungerSingle getInstance() {
        return HUNGER;
    }

    // 4、解决序列化破话单列
    public Object realResoler() {
        return HUNGER;
    }

}
