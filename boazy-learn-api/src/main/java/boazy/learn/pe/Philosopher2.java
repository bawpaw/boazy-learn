package boazy.learn.pe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 解决死锁：方法二
 *
 * 仅当哲学家的左右两支筷子都可用时，才允许他拿起筷子进餐。可以利用AND 型信号量机制实现，也可以利用信号量的保护机制实现
 *
 * @author boazy
 * @date 2019/5/6
 */
public class Philosopher2 implements Runnable {
    private Semaphore[] chopsticks;
    private Semaphore mutex;
    private int i;

    public Philosopher2(Semaphore[] chopsticks, Semaphore metux, int i) {
        this.chopsticks = chopsticks;
        this.mutex = metux;
        this.i = i;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {
                mutex.acquire();
                // 先拿起（请求）左手筷子
                chopsticks[i].acquire();
                // 再拿起（请求）右手筷子
                chopsticks[(i + 1) % 5].acquire();
                mutex.release();

                // 哲学家开始吃饭
                System.out.println("哲学家" + (i + 1) + "号：我在吃饭了...");

                // 先放下（释放）左手筷子
                chopsticks[i].release();
                // 再放下（释放）右手筷子
                chopsticks[(i + 1) % 5].release();

                // 哲学家开始思考
                System.out.println("哲学家" + (i + 1) + "号：我已吃饱开始思考了...");

                Thread.yield();
            } catch (InterruptedException e) {
                // TODO 演示代码不考虑异常问题的处理
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 创建大小为5的信号量数组，模拟5根筷子
        Semaphore[] chopsticks = new Semaphore[5];
        // 这个过程需要判断两根筷子是否可用，并保护起来
        Semaphore mutex = new Semaphore(1, true);
        // 初始化信号量
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Semaphore(1, true);
        }

        // 创建一个5个线程的线程池
        ExecutorService es = Executors.newFixedThreadPool(5);

        // 创建5个哲学家 但这样有可能会产生死锁问题
        for (int i = 0; i < 5; i++) {
            es.execute(new Philosopher2(chopsticks, mutex, i));
        }

    }

}
