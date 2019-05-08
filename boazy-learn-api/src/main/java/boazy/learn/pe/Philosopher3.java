package boazy.learn.pe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 解决死锁：方法三
 * <p>
 * 规定奇数号的哲学家先拿起他左边的筷子，然后再去拿他右边的筷子；而偶数号的哲学家则先拿起他右边的筷子，然后再去拿他左边的筷子
 *
 * @author boazy
 * @date 2019/5/6
 */
public class Philosopher3 implements Runnable {
    private Semaphore[] chopsticks;
    private int i;

    public Philosopher3(Semaphore[] chopsticks, int i) {
        this.chopsticks = chopsticks;
        this.i = i;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                // 奇数号哲学家，先左后右
                if ((i + 1) % 2 != 0) {
                    // 先拿起（请求）左手筷子
                    chopsticks[i].acquire();
                    // 再拿起（请求）右手筷子
                    chopsticks[(i + 1) % 5].acquire();
                }
                // 偶数号哲学家，先右后左
                else {
                    // 先拿起（请求）右手筷子
                    chopsticks[(i + 1) % 5].acquire();
                    // 再拿起（请求）左手筷子
                    chopsticks[i].acquire();
                }

                // 哲学家开始吃饭
                System.out.println("哲学家" + (i + 1) + "号：我在吃饭了...");

                // 奇数号哲学家，先左后右
                if ((i + 1) % 2 != 0) {
                    // 先放下（释放）左手筷子
                    chopsticks[i].release();
                    // 再放下（释放）右手筷子
                    chopsticks[(i + 1) % 5].release();
                }
                // 偶数号哲学家，先右后左
                else {
                    // 先放下（释放）右手筷子
                    chopsticks[(i + 1) % 5].release();
                    // 先放下（释放）右手筷子
                    chopsticks[i].release();
                }

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
        // 初始化信号量
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Semaphore(1, true);
        }

        // 创建一个5个线程的线程池
        ExecutorService es = Executors.newFixedThreadPool(5);

        // 创建5个哲学家 但这样有可能会产生死锁问题
        for (int i = 0; i < 5; i++) {
            es.execute(new Philosopher3(chopsticks, i));
        }

    }

}
