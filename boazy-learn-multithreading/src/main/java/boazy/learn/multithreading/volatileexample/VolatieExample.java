package boazy.learn.multithreading.volatileexample;

/**
 * volatile
 * <p>
 * 修饰变量，主要作用是使变量在多个线程间可见（实现主内存和线程工作内存可见）
 * <p>
 * 性能比synchronized强很多，不会造成阻塞
 * <p>
 * volatile关键字非原子性（不具备同步性）；只具有可见性没有原子性（要实现原子性建议使用atomic类系列对象）
 * <p>
 * 在访问volatile变量时不会执行加锁操作，因此也就不会使执行线程阻塞，因此volatile是一种比sychronized关键字更轻量级的同步机制
 * <p>
 * 解决问题：可见性（保证内存可见性）、指令重排序（防止指令重排）
 * <p>
 * 应用场景：
 * <p>
 * 内存屏障(保证数据可见性、防止指令之前重排序)：
 * 在每个volatile写操作的前面插入一个StoreStore屏障
 * 在每个volatile写操作的后面插入一个StoreLoad屏障
 * 在每个volatile读操作的后面插入一个LoadLoad屏障
 * 在每个volatile读操作的后面插入一个LoadStore屏障
 *
 * @author boazy
 * @company boazy
 * @date 2018/8/17
 */
public class VolatieExample {

    private static volatile boolean disconnect = false;

    public static void main(String[] args) {
        for (int i = 0, in = 5; i < in; i++) {
            new Thread(() -> {
                while (true) {
                    if (disconnect) {
                        System.out.println(Thread.currentThread().getName() + ": disconnect.");
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + ": running...");
                }
            }).start();
        }

        try {
            long startTime = System.currentTimeMillis();
            Thread.sleep(1000L * 15);
            System.out.println("时长(ms)：" + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disconnect = true; // 容断
    }

}
