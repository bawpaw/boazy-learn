package boazy.learn.pe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 解决死锁：方法一
 * <p>
 * 至多只允许四个哲学家同时进餐，以保证至少有一个哲学家能够进餐，最终总会释放出他所使用过的两支筷子，从而可使更多的哲学家进餐
 *
 * @author boazy
 * @date 2019/5/6
 */
public class Philosopher1 implements Runnable {
    private Semaphore[] chopsticks;
    private Semaphore count;
    private int i;

    public Philosopher1(Semaphore[] chopsticks, Semaphore count, int i) {
        this.chopsticks = chopsticks;
        this.count = count;
        this.i = i;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {
                // 抢占count
                count.acquire();
                // 先拿起（请求）左手筷子
                chopsticks[i].acquire();
                // 再拿起（请求）右手筷子
                chopsticks[(i + 1) % 5].acquire();

                // 哲学家开始吃饭
                System.out.println("哲学家" + (i + 1) + "号：我在吃饭了...");

                // 先放下（释放）左手筷子
                chopsticks[i].release();
                // 再放下（释放）右手筷子
                chopsticks[(i + 1) % 5].release();
                // 释放count
                count.release();

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
        // 限制只允许4个哲学家同时进餐
        Semaphore count = new Semaphore(4, true);
        // 初始化信号量
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Semaphore(1, true);
        }

        // 创建一个5个线程的线程池
        ExecutorService es = Executors.newFixedThreadPool(5);

        // 创建5个哲学家 但这样有可能会产生死锁问题
        for (int i = 0; i < 5; i++) {
            es.execute(new Philosopher1(chopsticks, count, i));
        }

    }

}
