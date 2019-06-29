package boazy.learn.designpattern.singleton.sluggard;

/**
 * 懒汉式单例（双重检查）
 * <p>
 * 被调用时才创建实例
 *
 * @author boazy
 * @date 2019/3/9
 */
public class SluggardSimpleSingleton {
    // 2、私有静态成员常量（用到时初始实例）
    private static SluggardSimpleSingleton sluggard;

    // 1、构造方法私有化
    private SluggardSimpleSingleton() {
        // 添加判断避免通过反射创建实例破坏单例
        if (null != sluggard) {
            throw new RuntimeException("Single instance class cannot create multiple instances");
        }
    }

    // 3、提供静态获取实例方法
    // synchronized保证线程安全，保证一个实例
    public static synchronized SluggardSimpleSingleton getInstance() {
        sluggard = new SluggardSimpleSingleton();
        return sluggard;
    }

    // 4、解决序列化破坏单列
    public Object realResoler() {
        return sluggard;
    }

}
