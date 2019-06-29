package boazy.learn.designpattern.singleton.hungry;

/**
 * 饿汉式（饥饿式）单例
 * <p>
 * 在类首次加载时就创建实例
 * <p>
 * 浪费内存空间
 *
 * @author boazy
 * @date 2019/3/9
 */
public class HungrySingleton {
    // 2、私有静态成员常量初始本类实例(类加载时初始化实例)
    private static final HungrySingleton HUNGRY = new HungrySingleton();

    // 1、构造方法私有化
    private HungrySingleton() {
        // 添加判断避免通过反射创建实例破坏单例
        if (null != HUNGRY) {
            throw new RuntimeException("Single instance class cannot create multiple instances");
        }
    }

    // 3、提供静态获取实例方法
    public static HungrySingleton getInstance() {
        return HUNGRY;
    }

    // 4、解决序列化破坏单列
    public Object realResoler() {
        return HUNGRY;
    }

}
