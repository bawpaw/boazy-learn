package boazy.learn.pe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 存在死锁问题
 * <p>
 * 放在桌子上的筷子是临界资源，在一段时间内只允许一位哲学家使用，
 * 为了实现对筷子的互斥访问，可以用一个信号量表示筷子，由这五个信号量构成信号量数组。
 *
 * @author boazy
 * @date 2019/5/6
 */
public class Philosopher implements Runnable {
    private Semaphore[] chopsticks;
    private int i;

    public Philosopher(Semaphore[] chopsticks, int i) {
        this.chopsticks = chopsticks;
        this.i = i;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                // 拿起（请求）左手筷子
                chopsticks[i].acquire();
                // 拿起（请求）左手筷子
                chopsticks[(i + 1) % 5].acquire();

                // 哲学家开始吃饭
                System.out.println("哲学家" + (i + 1) + "号：我在吃饭了...");

                // 放下（释放）左手筷子
                chopsticks[i].release();
                // 放下（释放）右手筷子
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

        // 创建一个5个线程的线程池
        ExecutorService es = Executors.newFixedThreadPool(5);

        // 初始化信号量
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Semaphore(1, true);
        }

        // 创建5个哲学家 但这样有可能会产生死锁问题
        for (int i = 0; i < 5; i++) {
            es.execute(new Philosopher(chopsticks, i));
        }
    }

}
