package boazy.learn.designpattern.singleton.sluggard;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 懒汉式单例（双重检查）
 * <p>
 * 被调用时才创建实例
 *
 * @author boazy
 * @date 2019/3/9
 */
public class SluggardDoubleCheckSingleton {
    // 2、私有静态成员常量（用到时初始实例）
    // 采用volatile修饰解决指令重排序破坏单例问题
    private volatile static SluggardDoubleCheckSingleton sluggard;

    private static final ReentrantLock LOCK = new ReentrantLock();

    // 1、构造方法私有化
    private SluggardDoubleCheckSingleton() {
        // 添加判断避免通过反射创建实例破坏单例
        if (null != sluggard) {
            throw new RuntimeException("Single instance class cannot create multiple instances");
        }
    }

    // 3、提供静态获取实例方法
    public static final SluggardDoubleCheckSingleton getInstance() {
        // 双重检查（第一重检查）
        if (null != sluggard) {
            return sluggard;
        }

        synchronized (SluggardDoubleCheckSingleton.class) {
            // 双重检查（第二重检查）
            if (null != sluggard) {
                return sluggard;
            }

            sluggard = new SluggardDoubleCheckSingleton();
        }

        return sluggard;
    }

    // 4、解决序列化破坏单列
    public Object realResoler() {
        return sluggard;
    }

}
