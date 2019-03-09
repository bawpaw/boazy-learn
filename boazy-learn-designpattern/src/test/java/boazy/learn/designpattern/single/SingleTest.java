package boazy.learn.designpattern.single;

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
                    System.out.println(System.currentTimeMillis() + ": " + HungrySingle.getInstance());
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
                    System.out.println(System.currentTimeMillis() + ": " + SluggardSingle.getInstance());
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
                    System.out.println(System.currentTimeMillis() + ": " + StaticInnerClassSingle.getInsatance());
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
                    System.out.println(System.currentTimeMillis() + ": " + EnumSingle.INSTANCE.hashCode());
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
