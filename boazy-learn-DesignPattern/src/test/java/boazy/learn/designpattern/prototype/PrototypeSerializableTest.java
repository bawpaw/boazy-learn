package boazy.learn.designpattern.prototype;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/5
 */
public class PrototypeSerializableTest {

    @Test
    public void testSerializable1() {
        MyHouse myHouse = new MyHouse();
        myHouse.setName("我的房子");
        myHouse.setPosition("广东省广州市增城区");
        myHouse.setTel("82288118");
        myHouse.setTotalFloorNumber(3);
        MyTV myTV;
        myHouse.setMyTV(myTV = new MyTV());
        myTV.setLength(2000);
        myTV.setHigh(1500);
        myTV.setWidth(100);
        TVCpu tvCpu;
        myTV.setTvCpu(tvCpu = new TVCpu());
        tvCpu.setName("冲天CPU");
        tvCpu.setKernelNumber(8);
        List<TVMemory> tvMemories;
        myTV.setTvMemories(tvMemories = new ArrayList<TVMemory>());
        TVMemory tvMemory1;
        tvMemories.add(tvMemory1 = new TVMemory());
        tvMemory1.setName("冲天Memory1");
        tvMemory1.setSize(8);
        TVMemory tvMemory2;
        tvMemories.add(tvMemory2 = new TVMemory());
        tvMemory2.setName("冲天Memory2");
        tvMemory2.setSize(4);

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        MyHouse myHouseCopy = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(myHouse);
            byte[] bytes = bos.toByteArray();

            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);

            myHouseCopy = (MyHouse) ois.readObject();   //克隆好的对象！
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if(null != bos) {
                try {
                    bos.close();
                } catch (Throwable t) {
                }
            }
            if(null != oos) {
                try {
                    oos.close();
                } catch (Throwable t) {
                }
            }
            if(null != bis) {
                try {
                    bis.close();
                } catch (Throwable t) {
                }
            }
            if(null != ois) {
                try {
                    ois.close();
                } catch (Throwable t) {
                }
            }
        }

        ProtorypeTestHelper.assertObject(myHouse, myHouseCopy);
    }

    @Test
    public void testSerializable2() {
        MyHouse myHouse = new MyHouse();
        myHouse.setName("我的房子");
        myHouse.setPosition("广东省广州市增城区");
        myHouse.setTel("82288118");
        myHouse.setTotalFloorNumber(3);
        MyTV myTV;
        myHouse.setMyTV(myTV = new MyTV());
        myTV.setLength(2000);
        myTV.setHigh(1500);
        myTV.setWidth(100);
        TVCpu tvCpu;
        myTV.setTvCpu(tvCpu = new TVCpu());
        tvCpu.setName("冲天CPU");
        tvCpu.setKernelNumber(8);
        List<TVMemory> tvMemories;
        myTV.setTvMemories(tvMemories = new ArrayList<TVMemory>());
        TVMemory tvMemory1;
        tvMemories.add(tvMemory1 = new TVMemory());
        tvMemory1.setName("冲天Memory1");
        tvMemory1.setSize(8);
        TVMemory tvMemory2;
        tvMemories.add(tvMemory2 = new TVMemory());
        tvMemory2.setName("冲天Memory2");
        tvMemory2.setSize(4);

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        MyNatatorium myNatatorium = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(myHouse);
            byte[] bytes = bos.toByteArray();

            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);

            /**
             * TODO 注意事项
             * 1、序列化不可以复制不同class对象，会抛异常的（java.lang.ClassCastException）
             * 2、序列化serialVersionUID与反序列化serialVersionUID不同，会抛异常
             * 3、序列化类与反序列化类字段差异不响应正常反序列化，响应的仅是字段序列化不到而已
             * 4、transient修饰的成员变量不会被序列化；static修饰的静态成员变更不会被序列化
             * 5、针对第3点绕过transient修饰不序列化使之序列化，序列化类写方法，如下：
             * private void writeObject(ObjectOutputStream objectOutputStream) {...}
             * private void readObject(ObjectInputStream objectInputStream) {...}
             * 6、父类也要实现Serializable接口否则会抛异常；父类已实现Serializable接口，子类可以不实现Serializable接口
             * 7、实现Cloneable接口克隆为浅克隆，浅克隆不会克隆复杂对象（或自定义对象）而是引用原来对象地址
             */
            myNatatorium = (MyNatatorium) ois.readObject();   //克隆好的对象！
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if(null != bos) {
                try {
                    bos.close();
                } catch (Throwable t) {
                }
            }
            if(null != oos) {
                try {
                    oos.close();
                } catch (Throwable t) {
                }
            }
            if(null != bis) {
                try {
                    bis.close();
                } catch (Throwable t) {
                }
            }
            if(null != ois) {
                try {
                    ois.close();
                } catch (Throwable t) {
                }
            }
        }

        ProtorypeTestHelper.assertObject(myHouse, myNatatorium);
    }

}
