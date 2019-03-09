package boazy.learn.designpattern.single.sluggard;

/**
 * 懒汉式单例（静态内部类）
 * <p>
 * 被调用时才创建实例
 * <p>
 * 比较牛B的实现方式，性能最优
 *
 * @author boazy
 * @date 2019/3/9
 */
public class SluggardInnerClassSingleton {

    // 1、构造方法私有化
    private SluggardInnerClassSingleton() {
        // 添加判断避免通过反射创建实例破坏单例
        if (null != StaticInnerClassHolder.SLUGGARD) {
            throw new RuntimeException("Single instance class cannot create multiple instances");
        }
    }

    // 2、内部静态类实例化对象
    // 内部静态类程序启动时不会加载执行的
    // 只要显示调用getInsatance()方法才会装载StaticInnerClassHolder类从而实例化
    private static class StaticInnerClassHolder {
        private static final SluggardInnerClassSingleton SLUGGARD = new SluggardInnerClassSingleton();
    }

    // 3、提供静态获取实例方法
    public static SluggardInnerClassSingleton getInstance() {
        return StaticInnerClassHolder.SLUGGARD;
    }

    // 4、解决序列化破坏单列
    public Object realResoler() {
        return StaticInnerClassHolder.SLUGGARD;
    }

}
