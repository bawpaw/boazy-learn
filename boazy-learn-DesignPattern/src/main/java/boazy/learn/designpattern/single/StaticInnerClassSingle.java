package boazy.learn.designpattern.single;

/**
 * 静态内部类单例模式
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/4
 */
public class StaticInnerClassSingle {
    private static boolean initialized = false;

    // 1、构造方法私有化
    private StaticInnerClassSingle() {
        // 4、解决反射破坏到单例
        synchronized (StaticInnerClassSingle.class) {
            if (!initialized) {
                initialized = true;
            } else {
                throw new RuntimeException("禁止初始化...");
            }
        }
    }

    // 2、内部静态类实例化对象
    // 只要显示调用getInsatance()方法才会装载StaticInnerClassHolder类从而实例化
    private static class StaticInnerClassHolder {
        private static final StaticInnerClassSingle STATIC_INNER_CLASS = new StaticInnerClassSingle();
    }

    // 3、提供静态获取实例方法
    public static StaticInnerClassSingle getInsatance() {
        return StaticInnerClassHolder.STATIC_INNER_CLASS;
    }

}
