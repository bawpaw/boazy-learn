package boazy.learn.designpattern.single.threadlocl;

/**
 * ThreadLocal单例
 * <p>
 * 同一线程单例，不同线程不同实例
 *
 * @author boazy
 * @date 2019/3/9
 */
public class ThreadLocalSingleton {

    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL_INSTANCE =
            ThreadLocal.withInitial(ThreadLocalSingleton::new);

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return THREAD_LOCAL_INSTANCE.get();
    }

}
