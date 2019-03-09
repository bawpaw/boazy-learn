package boazy.learn.designpattern.single;

import boazy.learn.designpattern.single.hungry.HungrySingleton;
import boazy.learn.designpattern.single.registered.EnumSingleton;
import boazy.learn.designpattern.single.sluggard.SluggardInnerClassSingleton;
import boazy.learn.designpattern.single.sluggard.SluggardSimpleSingleton;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/4
 */
public class SingleTest {

    @Test
    public void testHunger() {
        int count = 200;
        final CountDownLatch latch = new CountDownLatch(count);

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis() + ": " + HungrySingleton.getInstance());
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
            System.out.println("Hunger耗时(ms)：" + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSluggard() {
        int count = 200;
        final CountDownLatch latch = new CountDownLatch(count);

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis() + ": " + SluggardSimpleSingleton.getInstance());
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
            System.out.println("Sluggard耗时(ms)：" + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStaticInnerClass() {
        int count = 200;
        final CountDownLatch latch = new CountDownLatch(count);

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis() + ": " + SluggardInnerClassSingleton.getInstance());
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
            System.out.println("StaticInnerClass耗时(ms)：" + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEnum() {
        int count = 200;
        final CountDownLatch latch = new CountDownLatch(count);

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis() + ": " + EnumSingleton.INSTANCE.getData());
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
            System.out.println("Enum耗时(ms)：" + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
