package boazy.learn.designpattern.single;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 懒汉单例模式
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/4
 */
public class SluggardSingle {
    // 1、私有静态成员常量（用到时初始实例）
    private static SluggardSingle sluggard;

    private static final ReentrantLock LOCK = new ReentrantLock();

    // 2、构造方法私有化
    private SluggardSingle() {
    }

    // 3、提供静态获取实例方法
    public static SluggardSingle getInstance() {
        if(null != sluggard) {
            return sluggard;
        }

        LOCK.lock();
        try {
            if (null != sluggard) {
                return sluggard;
            }

            sluggard = new SluggardSingle();
            return sluggard;
        } finally {
            LOCK.unlock();
        }
    }

}
